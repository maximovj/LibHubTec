import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../services/auth-service.service';
import { LoginRequest } from '../../interfaces';

@Component({
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterModule,
    CheckboxModule,
    ButtonModule,
    InputTextModule,
    ProgressSpinnerModule,
  ],
  templateUrl: './recover-password.component.html',
  styles: ``
})
export class ForgetPasswordComponent implements OnInit {

  private toastrService = inject(ToastrService);

  private authService = inject(AuthService);

  public router = inject(Router);

  private fb = inject(FormBuilder);

  public myForm :FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
  });

  constructor(){}

  ngOnInit(): void { }

  onRecoverPassword()
  {
    if(!this.myForm.valid) {
      this.toastrService.error('Revise que el correo electrónico sea válido.','Recuperación de cuenta');
      return;
    }

    const login :LoginRequest = {
      email: this.myForm.get('email')?.value || '',
      password: ''
    };

    this.authService.forgetPassword(login)
    .subscribe(() =>{
      this.myForm.reset();
      this.toastrService.success('Se enviará un código a su corre electrónico.', 'Recuperación de cuenta');
    });
  }

  getQuery () : boolean
  {
    return this.authService.query();
  }

}
