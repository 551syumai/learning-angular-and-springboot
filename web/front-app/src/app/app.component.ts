import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  isAdmin = false;
  isUser = false;
  username?: string;

  constructor(private tokenService: TokenService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.isAdmin = this.roles.includes('ROLE_ADMIN');
      this.isUser = this.roles.includes('ROLE_USER');

      this.username = user.username;
    } else {
      this.router.navigate(['login']);
    }
  }

  logout(): void {
    this.tokenService.logOut();
    window.location.reload();
  }
}
