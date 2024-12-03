import { Component, inject, OnInit } from '@angular/core';
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service.service';

import { ToastrService } from 'ngx-toastr';

@Component({
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterModule,
    CheckboxModule,
    ButtonModule,
    InputTextModule,
  ],
  selector: 'auth-login-page',
  templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit {

  private toastr = inject(ToastrService);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private authService = inject(AuthService);

  public myForm :FormGroup = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  constructor() { }

  ngOnInit() { }

  onSubmit() :void {
    if(!this.myForm.valid) return;
    const { email, password } = this.myForm.value;

    this.authService
      .login({
        email,
        password,
      })
      .subscribe({
        next: () => {
          this.toastr.success('Haz iniciado sesión correctamente', 'Inicio de sesión');
          this.router.navigateByUrl('/books/list');
        },
        error: () => {
          this.toastr.error('Usuario o contraseña incorrecta.', 'Inicio de sesión');
        }
      });
  }

  onSignIn() :void
  {
    this.router.navigate(['/books']);
  }

}
