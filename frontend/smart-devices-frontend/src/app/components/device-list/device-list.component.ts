import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { DeviceService } from '../../services/device.service';
import { Device, DeviceType, DeviceTypeLabels } from '../../models/device';

@Component({
  selector: 'app-device-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './device-list.component.html',
  styleUrl: './device-list.component.css'
})
export class DeviceListComponent implements OnInit {
  devices: Device[] = [];
  filteredDevices: Device[] = [];
  deviceTypeLabels = DeviceTypeLabels;
  loading = true;
  errorMessage = '';
  
  // Paginación
  currentPage = 1;
  itemsPerPage = 12;
  totalPages = 1;

  constructor(
    private deviceService: DeviceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    console.log('DeviceListComponent initialized');
    this.loadDevices();
    
    // Escuchar parámetros de la URL
    this.route.queryParams.subscribe(params => {
      console.log('Query params:', params);
    });
  }

  loadDevices() {
    console.log('Loading devices...');
    this.loading = true;
    this.errorMessage = '';
    
    this.deviceService.getAllDevices().subscribe({
      next: (devices) => {
        console.log('Devices received:', devices);
        this.devices = devices;
        this.filteredDevices = [...devices];
        this.calculateTotalPages();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading devices:', error);
        this.errorMessage = `Error: ${error.message}`;
        this.loading = false;
      }
    });
  }

  calculateTotalPages() {
    this.totalPages = Math.ceil(this.filteredDevices.length / this.itemsPerPage);
    console.log('Total pages calculated:', this.totalPages);
  }

  get paginatedDevices(): Device[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    const result = this.filteredDevices.slice(startIndex, endIndex);
    console.log('Paginated devices:', result);
    return result;
  }

  getDeviceTypeLabel(type: DeviceType): string {
    return this.deviceTypeLabels[type] || type;
  }
}
