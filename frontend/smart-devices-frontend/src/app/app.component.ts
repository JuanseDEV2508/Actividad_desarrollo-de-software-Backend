import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Smart Devices';
  searchTerm: string = '';
  message: string = '';
  errorMessage: string = '';

  onSearch() {
    if (this.searchTerm.trim()) {
      // Implementar búsqueda
      console.log('Buscando:', this.searchTerm);
    }
  }

  setMessage(msg: string) {
    this.message = msg;
    setTimeout(() => this.clearMessage(), 5000);
  }

  setErrorMessage(msg: string) {
    this.errorMessage = msg;
    setTimeout(() => this.clearErrorMessage(), 5000);
  }

  clearMessage() {
    this.message = '';
  }

  clearErrorMessage() {
    this.errorMessage = '';
  }
}
