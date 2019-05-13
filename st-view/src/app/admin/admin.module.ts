import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
/**echarts */
import { NgxEchartsModule } from 'ngx-echarts';

/**拦截器 */
import { httpInterceptorProviders } from '../http-interceptors/index';

import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import zh from '@angular/common/locales/zh';

import { AdminComponent } from './admin/admin.component';
import { TestlistComponent } from './testlist/testlist.component';
import { TestresultComponent } from './testresult/testresult.component';
import { DashboardComponent } from './dashboard/dashboard.component';

registerLocaleData(zh);

@NgModule({
  declarations: [AdminComponent, TestlistComponent, TestresultComponent, DashboardComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxEchartsModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }, httpInterceptorProviders]
})
export class AdminModule { }
