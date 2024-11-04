import { Component, inject, OnInit } from '@angular/core';
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Router, RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    RouterModule,
    CheckboxModule,
    ButtonModule,
    InputTextModule,
  ],
  selector: 'auth-login-page',
  templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit {

  private router = inject(Router);

  constructor() { }

  ngOnInit() { }

  onSignIn() :void
  {
    this.router.navigate(['/books']);
  }



}
