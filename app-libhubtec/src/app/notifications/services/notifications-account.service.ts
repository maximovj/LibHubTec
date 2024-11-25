import { computed, inject, Injectable, signal } from "@angular/core";
import { NotificationAccount, NotificationAccountResponse } from "../interfaces";
import { HttpClient } from "@angular/common/http";
import { catchError, delay, map, Observable, of, throwError } from "rxjs";
import { User } from "../../shared/interfaces";

@Injectable({ providedIn: "root" })
export class NotificationsAccountService {

  private http = inject(HttpClient);

  constructor() { }

  public loadNotificationsAccount(user :User | null) :Observable<NotificationAccount[]>
  {
    const _token = this.getToken();
    if(!_token || !user) {
      return of([]);
    }

    return this.http.get<NotificationAccountResponse>(`http://localhost:5800/v1/notification/accounts/3/account/list`, {
      headers: {
        'Authorization': `${_token}`
      }
    })
    .pipe(
      map(({data, response}) => {
        if(response && data && response.success && data.length > 0) {
          return data;
        }
        return [];
      }),
      catchError( err  => throwError(( ) => err.message)),
    );
  }

  public readNotification(notificationAccount :NotificationAccount) :Observable<NotificationAccount | null>
  {

    const _token = this.getToken();
    if(!_token) {
      return of(null);
    }

    return this.http.put<NotificationAccountResponse>(`http://localhost:5800/v1/notification/accounts/${notificationAccount.id}/notification/read`,null, {
      headers: {
        'Authorization': `${_token}`,
      }
    })
    .pipe(
      map(({response, data}) => {
        if(response && data && response.success && data.length > 0) {
          return data[0];
        }
        return null;
      }),
      catchError( (err) => throwError( () => err.message)),
    );
  }

  private getToken() :string | null
  {
    const _token = localStorage.getItem('_token');
    if(!_token) {
      return null;
    }

    return `Bearer ${_token}`;
  }

}
