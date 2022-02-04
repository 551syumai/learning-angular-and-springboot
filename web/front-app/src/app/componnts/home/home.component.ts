import { Component, OnInit } from '@angular/core';
import { Auth } from '../../services/auth';

import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  userData: Auth;
  errorMessage = null;

  constructor(private token: TokenService) {
    this.userData = this.token.getUser();
  }

  ngOnInit() {}
}
