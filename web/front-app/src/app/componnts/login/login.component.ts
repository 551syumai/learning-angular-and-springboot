import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { TokenService } from '../../services/token.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Auth } from '../../services/auth';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  
  constructor(
    private authService: AuthService,
    private token: TokenService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.token.getUser().roles;
      this.router.navigate(['']);
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      (response: Auth) => {
        this.token.saveToken(response.accessToken);
        this.token.saveUser(response);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.token.getUser().roles;
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error.error);
        alert(error.error.messages);
      }
    );
  }
}
