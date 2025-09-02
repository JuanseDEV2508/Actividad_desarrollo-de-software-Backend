import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { DeviceService } from '../../services/device.service';
import { Device, DeviceTypeLabels } from '../../models/device';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  latestDevices: Device[] = [];
  deviceTypeLabels = DeviceTypeLabels;
  loading = true;
  errorMessage = '';

  constructor(private deviceService: DeviceService) {}

  ngOnInit() {
    console.log('HomeComponent initialized');
    this.loadLatestDevices();
  }

  loadLatestDevices() {
    console.log('Loading latest devices...');
    this.loading = true;
    this.errorMessage = '';
    
    this.deviceService.getLatestDevices().subscribe({
      next: (devices) => {
        console.log('Devices received:', devices);
        this.latestDevices = devices;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading latest devices:', error);
        this.errorMessage = `Error: ${error.message}`;
        this.loading = false;
      }
    });
  }
}
