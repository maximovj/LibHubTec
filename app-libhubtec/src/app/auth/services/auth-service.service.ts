import { LoginRequest } from './../interfaces/login-request.interface';
import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { LoginResponse } from '../interfaces/login-response.interface';
import { catchError, map, Observable, take, throwError } from 'rxjs';
import { User } from '../interfaces';
import { AuthStatus } from '../interfaces/auth-status.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http  = inject(HttpClient);

  private _user = signal<User |null>(null);
  public user = computed(() => this._user());

  private _authStatus = signal<AuthStatus>(AuthStatus.checking);
  public authStatus = computed(() => this._authStatus());

  constructor() { }

  login(req :LoginRequest) :Observable<boolean>
  {
    const query = this.http.post<LoginResponse>('http://localhost:5800/v1/auth/authenticate', {
      username: req.username,
      email: req.email,
      password: req.password,
    });

    const _pipe =  query
    .pipe(
      map( ({data, response}) => {
        if(response?.success && data?.token ) {
          this._user.set({
            username: req.username || 'Desconocido',
            email: req.email,
            password: req.password,
          });
          this._authStatus.set(AuthStatus.authenticated);
          localStorage.setItem('_token' , data.token);
        }
        return response.success || false;
      }),
      catchError( err => throwError(() => err.message)),
    );

    return _pipe;
  }

  isAuthenticated() :boolean
  {
    return this.authStatus() === AuthStatus.authenticated;
  }

}
