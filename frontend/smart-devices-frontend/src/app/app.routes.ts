import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { DeviceListComponent } from './components/device-list/device-list.component';
import { DeviceDetailComponent } from './components/device-detail/device-detail.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { DeviceFormComponent } from './components/admin/device-form/device-form.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'devices', component: DeviceListComponent },
  { path: 'devices/:id', component: DeviceDetailComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'admin/device/new', component: DeviceFormComponent },
  { path: 'admin/device/:id/edit', component: DeviceFormComponent },
  { path: '**', redirectTo: '' }
];
