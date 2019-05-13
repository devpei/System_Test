import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  login(user: object): Observable<any> {
    return this.http.post('/api/login', user, { withCredentials: true });
  }
}
