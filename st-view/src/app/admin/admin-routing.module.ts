import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin/admin.component';
import { TestlistComponent } from './testlist/testlist.component';
import { TestresultComponent } from './testresult/testresult.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { AuthGuard } from '../login/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'testlist',
        component: TestlistComponent
      },
      {
        path: 'testresult/:id',
        component: TestresultComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
