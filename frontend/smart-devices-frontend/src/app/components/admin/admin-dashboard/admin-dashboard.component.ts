import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { DeviceService } from '../../../services/device.service';
import { Device, DeviceTypeLabels } from '../../../models/device';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {
  devices: Device[] = [];
  deviceTypeLabels = DeviceTypeLabels;
  loading = true;
  errorMessage = '';
  
  // Estadísticas
  totalDevices = 0;
  devicesByType: { [key: string]: number } = {};
  devicesByBrand: { [key: string]: number } = {};
  averagePrice = 0;

  // Hacer Object disponible en el template
  Object = Object;

  constructor(private deviceService: DeviceService) {}

  ngOnInit() {
    console.log('AdminDashboardComponent initialized');
    this.loadDevices();
  }

  loadDevices() {
    console.log('Loading devices for admin dashboard...');
    this.loading = true;
    this.errorMessage = '';
    
    this.deviceService.getAllDevices().subscribe({
      next: (devices) => {
        console.log('Devices received for admin:', devices);
        this.devices = devices;
        this.calculateStatistics();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading devices for admin:', error);
        this.errorMessage = `Error: ${error.message}`;
        this.loading = false;
      }
    });
  }

  calculateStatistics() {
    this.totalDevices = this.devices.length;
    
    // Dispositivos por tipo
    this.devicesByType = {};
    this.devices.forEach(device => {
      this.devicesByType[device.type] = (this.devicesByType[device.type] || 0) + 1;
    });
    
    // Dispositivos por marca
    this.devicesByBrand = {};
    this.devices.forEach(device => {
      this.devicesByBrand[device.brand] = (this.devicesByBrand[device.brand] || 0) + 1;
    });
    
    // Precio promedio
    if (this.devices.length > 0) {
      const totalPrice = this.devices.reduce((sum, device) => sum + Number(device.price), 0);
      this.averagePrice = totalPrice / this.devices.length;
    }
  }

  getDeviceTypeLabel(type: string): string {
    return this.deviceTypeLabels[type as keyof typeof this.deviceTypeLabels] || type;
  }

  deleteDevice(deviceId: number | undefined) {
    if (deviceId && confirm('¿Estás seguro de que quieres eliminar este dispositivo?')) {
      this.deviceService.deleteDevice(deviceId).subscribe({
        next: () => {
          console.log('Device deleted successfully');
          this.loadDevices(); // Recargar la lista
        },
        error: (error) => {
          console.error('Error deleting device:', error);
          this.errorMessage = `Error al eliminar: ${error.message}`;
        }
      });
    }
  }
}
