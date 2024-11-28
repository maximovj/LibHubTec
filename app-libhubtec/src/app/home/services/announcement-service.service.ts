import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Announcement, AnnouncementResponse } from "../interfaces";
import { catchError, map, Observable, of, throwError } from "rxjs";

@Injectable({ providedIn: 'root' })
export class AnnouncementService {

  private httpClient = inject(HttpClient);

  public listAllAnnountcements() : Observable<Announcement[]>
  {
    const _token = this.getToken();
    if(!_token) {
      return of([]);
    }

    return this.httpClient.get<AnnouncementResponse>(`http://localhost:5800/v1/announcements`, {
      headers: {
        "Authorization" : `${_token}`,
      }
    })
    .pipe(
      map(({ data }) => {
        if(data && data.length > 0  ) {
          return data;
        }
        return [];
      }),
      catchError( err => throwError( () => err.message) ),
    );
  }

  private getToken() : string | null
  {
    const _token = localStorage.getItem('_token');
    if(!_token) {
      return null;
    }

    return `Bearer ${_token}`;
  }

}
