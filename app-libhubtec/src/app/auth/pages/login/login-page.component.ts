import { Component, OnInit } from '@angular/core';
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  standalone: true,
  imports: [
    CheckboxModule,
    ButtonModule,
    InputTextModule,
  ],
  selector: 'auth-login-page',
  templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit {
  constructor() { }

  ngOnInit() { }
}
