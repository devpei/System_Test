import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser'

import { AuthService } from './auth.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('登录');
    this.loginForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  login() {
    for (const i in this.loginForm.controls) {
      this.loginForm.controls[i].markAsDirty();
      this.loginForm.controls[i].updateValueAndValidity();
    }
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe((data: any) => {
        if (data.result === 1) {
          console.log('认证成功');
          localStorage.setItem('id', data.data.id);
          localStorage.setItem('name', data.data.name);
          localStorage.setItem('userName', data.data.userName);
          let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/admin';

          this.router.navigate([redirect]);
        } else {
          console.log('认证失败：' + data.message);
        }
      });
    }
  }

}
