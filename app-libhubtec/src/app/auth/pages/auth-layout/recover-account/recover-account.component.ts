import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { InputOtpModule } from 'primeng/inputotp';
import { DividerModule } from 'primeng/divider';
import { ToastrService } from 'ngx-toastr';

@Component({
  standalone: true,
  templateUrl: './recover-account.component.html',
  styles: `
  p-password > div,
  p-password > div > input {
    width: 100px !important;
  }

  `,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    CheckboxModule,
    DividerModule,
    ButtonModule,
    PasswordModule,
    InputTextModule,
    InputOtpModule,
  ],
})
export class RecoverAccountComponent implements OnInit {


  private activedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private toastrService = inject(ToastrService);

  private fb = inject(FormBuilder);

  public myForm : FormGroup = this.fb.group({
    'new_password': ['' , [Validators.required]],
    'confirm_password': ['', [Validators.required]],
  });

  private _isValidCode = signal<boolean>(false);
  public isValidCode = computed(() => this._isValidCode());

  public code = signal<string>('');

  constructor() { }

  ngOnInit(): void {
    this.readToken();
  }

  public btnValidateCode() : void
  {
    if(this.code().length === 0) {
      this.toastrService.info('Ingrese un código, por favor', 'Recuperar cuenta');
      return;
    }
    console.log('Válidar código:', this.code());
    this._isValidCode.set(true);
  }

  public onSubmit() :void
  {

    if(!this.myForm.valid) {
      this.toastrService.warning('Todos los campos son obligatorios');
      return;
    }

    if(this.myForm.get('new_password')?.value !== this.myForm.get('confirm_password')?.value ){
      this.toastrService.warning('La constrase no coindicen');
      return;
    }


    if(this.isValidCode()){
      alert('Establecer contraseña');
      this.toastrService.success('Cuenta recuperado con exito, inicia sesión a tu cuenta.', 'Recuperar cuenta');
      this.router.navigate(['/login']);
      this.reset();
    }
  }

  private reset() :void
  {
    this._isValidCode.set(false);
    this.code.set('');
    this.myForm.reset();
  }

  private readToken() :void
  {
    this.activedRoute.queryParamMap.subscribe((params) => {
      const _token = params.get('token');
      if(!_token || !this.validateToken(_token)) {
        this.reset();
        this.router.navigate(['/login']);
        return;
      }
    });
  }

  private validateToken(token :string | null) :boolean
  {
    if(!token) return false;

    console.log('Válidar token:',token);
    return true;
  }

}
