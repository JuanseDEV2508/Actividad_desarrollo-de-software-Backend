import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeviceService } from '../../../services/device.service';
import { Device, DeviceType, DeviceTypeLabels } from '../../../models/device';

@Component({
  selector: 'app-device-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './device-form.component.html',
  styleUrl: './device-form.component.css'
})
export class DeviceFormComponent implements OnInit {
  device: Device = {
    id: 0,
    name: '',
    brand: '',
    type: DeviceType.SMARTPHONE,
    description: '',
    price: 0,
    releaseDate: '',
    imageUrl: '',
    specifications: '',
    comments: []
  };

  deviceTypeLabels = DeviceTypeLabels;
  deviceTypes = Object.values(DeviceType);
  loading = false;
  errorMessage = '';
  isEditMode = false;
  submitted = false;

  constructor(
    private deviceService: DeviceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    console.log('DeviceFormComponent initialized');
    this.checkEditMode();
  }

  checkEditMode() {
    const deviceId = this.route.snapshot.paramMap.get('id');
    if (deviceId) {
      this.isEditMode = true;
      this.loadDevice(Number(deviceId));
    }
  }

  loadDevice(deviceId: number) {
    console.log('Loading device for edit:', deviceId);
    this.loading = true;
    this.errorMessage = '';
    
    this.deviceService.getDeviceById(deviceId).subscribe({
      next: (device) => {
        console.log('Device loaded for edit:', device);
        this.device = { ...device };
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading device for edit:', error);
        this.errorMessage = `Error: ${error.message}`;
        this.loading = false;
      }
    });
  }

  onSubmit() {
    console.log('Form submitted:', this.device);
    this.submitted = true;
    
    if (this.isFormValid()) {
      this.loading = true;
      this.errorMessage = '';
      
      if (this.isEditMode) {
        this.updateDevice();
      } else {
        this.createDevice();
      }
    }
  }

  isFormValid(): boolean {
    return !!(
      this.device.name &&
      this.device.brand &&
      this.device.description &&
      this.device.price > 0
    );
  }

  createDevice() {
    console.log('Creating new device:', this.device);
    
    this.deviceService.createDevice(this.device).subscribe({
      next: (newDevice) => {
        console.log('Device created successfully:', newDevice);
        alert('Dispositivo creado exitosamente');
        this.router.navigate(['/admin']);
      },
      error: (error) => {
        console.error('Error creating device:', error);
        this.errorMessage = `Error al crear el dispositivo: ${error.message}`;
        this.loading = false;
      }
    });
  }

  updateDevice() {
    console.log('Updating device:', this.device);
    
    this.deviceService.updateDevice(this.device.id!, this.device).subscribe({
      next: (updatedDevice) => {
        console.log('Device updated successfully:', updatedDevice);
        alert('Dispositivo actualizado exitosamente');
        this.router.navigate(['/admin']);
      },
      error: (error) => {
        console.error('Error updating device:', error);
        this.errorMessage = `Error al actualizar el dispositivo: ${error.message}`;
        this.loading = false;
      }
    });
  }

  cancel() {
    this.router.navigate(['/admin']);
  }

  getDeviceTypeLabel(type: DeviceType): string {
    return this.deviceTypeLabels[type] || type;
  }

  // Validación de campos
  isFieldInvalid(fieldName: string): boolean {
    const field = this.device[fieldName as keyof Device];
    return this.submitted && !field;
  }

  getFieldError(fieldName: string): string {
    if (this.isFieldInvalid(fieldName)) {
      return `${this.getFieldLabel(fieldName)} es requerido`;
    }
    return '';
  }

  getFieldLabel(fieldName: string): string {
    const labels: { [key: string]: string } = {
      name: 'Nombre',
      brand: 'Marca',
      type: 'Tipo',
      description: 'Descripción',
      price: 'Precio',
      releaseDate: 'Fecha de lanzamiento',
      imageUrl: 'URL de imagen',
      specifications: 'Especificaciones'
    };
    return labels[fieldName] || fieldName;
  }
}
