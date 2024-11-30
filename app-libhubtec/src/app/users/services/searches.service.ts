import { User } from './../../shared/interfaces/user.interface';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from "@angular/core";
import { SearchResponse } from '../interfaces/search-response.interface';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { SearchEntity } from '../interfaces';

@Injectable({ providedIn: 'root' })
export class SearchesService {

  private httpClient = inject(HttpClient);

  constructor() { }

  public listSearches( user: User| null) :Observable<SearchEntity[]>
  {
    const _token = this.getToken();
    if(!_token ||!user) {
      return of([]);
    }

    const headers = new HttpHeaders().set('Authorization', _token);
    const account_id = user.id;

    return this.httpClient.get<SearchResponse>(`http://localhost:5800/v1/searches/${account_id}/account`, { headers })
    .pipe(
      map( ({ data, response}) => {
        if(response.status && data && data.length > 0) {
          return data;
        }
        return [];
      }),
      catchError((err) => throwError(() => err.message)),
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
