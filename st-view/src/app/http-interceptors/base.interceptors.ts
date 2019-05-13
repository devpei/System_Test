import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { NzMessageService } from 'ng-zorro-antd';

import {
    HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse,
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class BaseInterceptor implements HttpInterceptor {

    constructor(private router: Router, private message: NzMessageService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpEvent<any>> {
        // let url = req.url;
        const newReq = req.clone({ withCredentials: true });
        return next.handle(newReq).pipe(
            tap((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    /**登录已过期 */
                    if (event.body.result === 100) {
                        console.log('==>登录过期，跳转登录页面');
                        /**跳转登录页面 */
                        localStorage.clear();
                        this.router.navigate(['/login']);
                        this.message.create('info', event.body.message);
                    }
                }
                return event;
            })
        );
    }
}