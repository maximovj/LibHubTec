import { Payload } from './../../../interfaces/payload.interface';
import { AuthService } from './../../../services/auth-service.service';
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
import { lastValueFrom } from 'rxjs';

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

  private authService = inject(AuthService);
  private activedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private toastrService = inject(ToastrService);

  private fb = inject(FormBuilder);

  public myForm : FormGroup = this.fb.group({
    'new_password': ['' , [Validators.required, Validators.minLength(8), Validators.maxLength(30)]],
    'confirm_password': ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]],
  });

  private _isValidCode = signal<boolean>(false);
  public isValidCode = computed(() => this._isValidCode());

  public code = signal<string>('');
  private payload :Payload | null = null;
  private _token : string | null = null;

  constructor() { }

  ngOnInit(): void {
    this.readToken();
  }

  private readToken() :void
  {
    this.activedRoute.queryParamMap.subscribe(async (params) => {
      this._token = params.get('token');
      const res$ = this.authService.checkToken(this._token);
      const _isValidToken = await lastValueFrom(res$);

      if(!_isValidToken) {
        this.toastrService.error('El token es inválido o está caducado.');
        this.reset();
        this.router.navigate(['/login']);
        return;
      }
      this.payload = this.authService.getPayload(this._token || '') || null;
    });
  }

  public btnValidateCode() : void
  {
    if(this.code().length === 0) {
      this.toastrService.info('Ingrese un código, por favor', 'Recuperar cuenta');
      return;
    }
    if(this.code() !== this.payload?.code){
      this.toastrService.error('El código ingresado es incorrecto', 'Recuperar cuenta');
      return;
    }
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
      alert(JSON.stringify({
        code: this.payload?.code,
        sub: this.payload?.sub,
        token: this._token,
      }, null, -2));
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

}