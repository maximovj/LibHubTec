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
  templateUrl: './recover-password.component.html',
  styles: ``
})
export class RecoverPasswordComponent implements OnInit {

  public router = inject(Router);

  constructor(){}

  ngOnInit(): void { }

  onRecoverPassword()
  {
    window.alert('Rebice su bandeja de entrada, para recuperar su cuenta.');
  }

}
