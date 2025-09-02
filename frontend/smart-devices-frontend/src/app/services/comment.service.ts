import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = 'http://localhost:8080/api/comments'; // Cambiaremos esto cuando configuremos CORS

  constructor(private http: HttpClient) { }

  // Obtener comentarios por dispositivo
  getCommentsByDeviceId(deviceId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiUrl}/device/${deviceId}`);
  }

  // Agregar comentario a un dispositivo
  addComment(deviceId: number, comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${this.apiUrl}/device/${deviceId}`, comment);
  }

  // Obtener promedio de calificación por dispositivo
  getAverageRatingByDeviceId(deviceId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/device/${deviceId}/average-rating`);
  }

  // Obtener cantidad de comentarios por dispositivo
  getCommentCountByDeviceId(deviceId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/device/${deviceId}/count`);
  }

  // Eliminar comentario
  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
