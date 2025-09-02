import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeviceService } from '../../services/device.service';
import { CommentService } from '../../services/comment.service';
import { Device, DeviceTypeLabels } from '../../models/device';
import { Comment } from '../../models/comment';

@Component({
  selector: 'app-device-detail',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './device-detail.component.html',
  styleUrl: './device-detail.component.css'
})
export class DeviceDetailComponent implements OnInit {
  device: Device | null = null;
  comments: Comment[] = [];
  deviceTypeLabels = DeviceTypeLabels;
  loading = true;
  errorMessage = '';
  
  // Hacer Math disponible en el template
  Math = Math;
  
  // Nuevo comentario
  newComment = {
    userName: '',
    commentText: '',
    rating: 5
  };
  
  // Estadísticas
  averageRating = 0;
  totalComments = 0;

  constructor(
    private deviceService: DeviceService,
    private commentService: CommentService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    console.log('DeviceDetailComponent initialized');
    this.loadDevice();
  }

  loadDevice() {
    const deviceId = this.route.snapshot.paramMap.get('id');
    if (!deviceId) {
      this.errorMessage = 'ID de dispositivo no válido';
      this.loading = false;
      return;
    }

    console.log('Loading device with ID:', deviceId);
    this.loading = true;
    this.errorMessage = '';
    
    this.deviceService.getDeviceById(Number(deviceId)).subscribe({
      next: (device) => {
        console.log('Device received:', device);
        this.device = device;
        this.loadComments(Number(deviceId));
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading device:', error);
        this.errorMessage = `Error: ${error.message}`;
        this.loading = false;
      }
    });
  }

  loadComments(deviceId: number) {
    console.log('Loading comments for device:', deviceId);
    
    this.commentService.getCommentsByDeviceId(deviceId).subscribe({
      next: (comments) => {
        console.log('Comments received:', comments);
        this.comments = comments;
        this.calculateStatistics(deviceId);
      },
      error: (error) => {
        console.error('Error loading comments:', error);
      }
    });
  }

  calculateStatistics(deviceId: number) {
    // Obtener promedio de rating
    this.commentService.getAverageRatingByDeviceId(deviceId).subscribe({
      next: (rating) => {
        this.averageRating = rating;
      },
      error: (error) => {
        console.error('Error loading average rating:', error);
      }
    });

    // Obtener total de comentarios
    this.commentService.getCommentCountByDeviceId(deviceId).subscribe({
      next: (count) => {
        this.totalComments = count;
      },
      error: (error) => {
        console.error('Error loading comment count:', error);
      }
    });
  }

  submitComment() {
    if (!this.device || !this.newComment.userName || !this.newComment.commentText) {
      alert('Por favor completa todos los campos');
      return;
    }

    const comment: Comment = {
      id: 0,
      userName: this.newComment.userName,
      commentText: this.newComment.commentText,
      rating: this.newComment.rating,
      createdAt: new Date().toISOString(),
      device: this.device
    };

    console.log('Submitting comment:', comment);
    
    this.commentService.addComment(this.device.id!, comment).subscribe({
      next: (newComment: Comment) => {
        console.log('Comment added successfully:', newComment);
        this.comments.unshift(newComment);
        this.calculateStatistics(this.device!.id!);
        
        // Limpiar formulario
        this.newComment = {
          userName: '',
          commentText: '',
          rating: 5
        };
      },
      error: (error: any) => {
        console.error('Error adding comment:', error);
        alert('Error al agregar el comentario');
      }
    });
  }

  getDeviceTypeLabel(type: string): string {
    return this.deviceTypeLabels[type as keyof typeof this.deviceTypeLabels] || type;
  }

  getStarRating(rating: number): string[] {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(i <= rating ? 'fas fa-star text-warning' : 'far fa-star text-muted');
    }
    return stars;
  }
}
