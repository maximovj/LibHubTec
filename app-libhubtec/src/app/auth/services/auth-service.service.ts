import { LoginRequest } from './../interfaces/login-request.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { LoginResponse } from '../interfaces/login-response.interface';
import { catchError, delay, map, Observable, of, take, tap, throwError } from 'rxjs';
import { AccountDetailsResponse, Payload, User, VerifyTokenResponse } from '../interfaces';
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

  constructor() {
    this.checkAuthentication().subscribe();
  }

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

  checkAuthentication() :Observable<boolean>
  {
    const token = localStorage.getItem('_token');

    if(!token){
      this._authStatus.set(AuthStatus.notAuthenticated);
      return of(false);
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this
      .http
      .get<VerifyTokenResponse>('http://localhost:5800/v1/auth/verify-token', { headers })
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

  private getPayload(token :string) :Payload {
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

  loadUserAndToken(token :string, payload :Payload) :void
  {
    const sub = parseInt(payload.sub);
    this._authStatus.set(AuthStatus.authenticated);
    localStorage.setItem('_token' , token);

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<AccountDetailsResponse>(`http://localhost:5800/v1/accounts/${sub}/details`, { headers })
    .pipe(
      map(({data}) => {
        const userData = data?.at(0);
        if(userData) {
          this._user.set({
            ...userData,
          })
        }
      }))
    .subscribe();
  }

}
