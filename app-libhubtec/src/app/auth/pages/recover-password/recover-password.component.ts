import { Component } from '@angular/core';
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { RouterModule } from '@angular/router';

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
export class RecoverPasswordComponent {

}
