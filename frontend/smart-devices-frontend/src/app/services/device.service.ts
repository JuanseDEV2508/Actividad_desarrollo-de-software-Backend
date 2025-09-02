import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Device, DeviceType } from '../models/device';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private apiUrl = 'http://localhost:8080/api/devices'; // Cambiaremos esto cuando configuremos CORS

  constructor(private http: HttpClient) { }

  // Obtener todos los dispositivos
  getAllDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.apiUrl);
  }

  // Obtener dispositivo por ID
  getDeviceById(id: number): Observable<Device> {
    return this.http.get<Device>(`${this.apiUrl}/${id}`);
  }

  // Obtener dispositivos por tipo
  getDevicesByType(type: DeviceType): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiUrl}/type/${type}`);
  }

  // Obtener dispositivos por marca
  getDevicesByBrand(brand: string): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiUrl}/brand/${brand}`);
  }

  // Buscar dispositivos
  searchDevices(searchTerm: string): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiUrl}/search?q=${searchTerm}`);
  }

  // Obtener últimos dispositivos
  getLatestDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiUrl}/latest`);
  }

  // Obtener todas las marcas
  getAllBrands(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/brands`);
  }

  // Crear dispositivo
  createDevice(device: Device): Observable<Device> {
    return this.http.post<Device>(this.apiUrl, device);
  }

  // Actualizar dispositivo
  updateDevice(id: number, device: Device): Observable<Device> {
    return this.http.put<Device>(`${this.apiUrl}/${id}`, device);
  }

  // Eliminar dispositivo
  deleteDevice(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
