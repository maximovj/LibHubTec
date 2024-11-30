import { HttpClient, HttpHeaders } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, delay, map, Observable, of, take, tap, throwError } from 'rxjs';

import {
  AccountDetailsResponse,
  LoginRequest,
  LoginResponse,
  Payload,
  RecoverAccountRequest,
  RecoverAccountResponse,
  VerifyTokenResponse
} from '../interfaces';

import { AuthStatus, User } from '../../shared/interfaces';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http  = inject(HttpClient);

  private _query = signal<boolean>(false);
  public query = computed(() => this._query());

  private _user = signal<User |null>(null);
  public user = computed(() => this._user());

  private _notifications = signal<number|null>(null);
  public notifications = computed(()=> this._notifications());

  private _authStatus = signal<AuthStatus>(AuthStatus.checking);
  public authStatus = computed(() => this._authStatus());

  constructor()
  {
    this.checkAuthStatus();
  }

  public getPayload(token :string) :Payload
  {
    return JSON.parse(atob(atob(token).split('').reverse().join('').split('.')[1]));
  }

  isAuthenticated() :boolean
  {
    return this.authStatus() === AuthStatus.authenticated;
  }

  logout() :void
  {
    this._authStatus.set(AuthStatus.notAuthenticated);
    this._user.set(null);
    localStorage.removeItem('_token');
  }

  // Obtener información de la cuenta enviando: `token`
  loadUserAndToken(token :string, payload :Payload) :void
  {
    const sub = parseInt(payload.sub || '');
    this._authStatus.set(AuthStatus.authenticated);
    localStorage.setItem('_token' , token);

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<AccountDetailsResponse>(`http://localhost:5800/v1/accounts/${sub}/details`, { headers })
    .pipe(
      map(({data, notifications}) => {
        const userData = data?.at(0);
        if(userData) {
          this._user.set({
            ...userData,
          })
        }
        this._notifications.set(notifications || 0);
      }))
    .subscribe();
  }

  // Entrar al sistema (login) enviando: `email`, `password`
  login(req :LoginRequest) :Observable<boolean>
  {
    return this.http.post<LoginResponse>('http://localhost:5800/v1/auth/authenticate', {
      username: req.username,
      email: req.email,
      password: req.password,
    }).pipe(
      map( ({data, response}) => {
        if(response?.success && data?.token ) {
          const token :string =  data?.token;
          const payload :Payload = this.getPayload(token);
          this.loadUserAndToken(token, payload);
        }
        return true;
      }),
      catchError( err => throwError(() => err.message)),
    );
  }

  // Definir el estado de autenticación
  private checkAuthStatus() :void
  {
    const token = localStorage.getItem('_token');

    if(!token){
      // Si no existe un token, se queda sin estado
      // es decir, no es autenticado, ni, no autenticado.
      this._authStatus.set(AuthStatus.notStatus);
      return;
    }

    this.verifyToken(token).subscribe();
  }

  // Válidar / Verificar si el token
  private verifyToken(token :string | null) :Observable<boolean>
  {
    return this
      .http
      .get<VerifyTokenResponse>('http://localhost:5800/v1/auth/verify-token', { headers: {
        'Authorization': `Bearer ${token}`
      } })
      .pipe(
        tap(() => {
          this._authStatus.set(AuthStatus.checking);
        }),
        delay(1000),
        map(({response, data}) => {
          if(response?.success && data?.refresh_token) {
            const token :string =  data?.refresh_token;
            const payload :Payload = this.getPayload(token);
            this.loadUserAndToken(token, payload);
          }
          return true;
        }),
        catchError( err => throwError(() => {
          this.logout();
          return err.message;
        })),
      );
  }

  // Verificar mi token de recuperación de cuenta
  public checkTokenRecoverAccount(token :string | null) :Observable<boolean>
  {
    return this
      .http
      .post<VerifyTokenResponse>('http://localhost:5800/v1/recover/verify-token', null, { headers: {
        'Authorization': `Bearer ${token}`
      } })
      .pipe(
        map(({response}) => {
          return response?.success;
        }),
        catchError( () => of(false)),
      );
  }

  // Enviar correo electrónico con token, código para recuperar mi cuenta
  forgetPassword(req :LoginRequest) : Observable<boolean>
  {
    this._query.set(true);
    return this.http.post<RecoverAccountResponse>('http://localhost:5800/v1/auth/forget-password', { email: req.email })
    .pipe(
      map( ({response}) => response.success),
      catchError(err => throwError(()=> err.message)),
      tap(() => {
        this._query.set(false);
      })
    );
  }

  // Recuperar mi cuenta enviando: `code`, `new_password`, `confirm_password`
  recoverAccountConfirm(req :RecoverAccountRequest, token :string) : Observable<boolean>
  {
    this._query.set(true);
    return this.http.post<RecoverAccountResponse>('http://localhost:5800/v1/recover/account', req, { headers: {
      'Authorization': `Bearer ${token}`
    } })
    .pipe(
      map( ({response}) => response.success),
      catchError(err => throwError(()=> err.message)),
      tap(() => {
        this._query.set(false);
      })
    );
  }

}
