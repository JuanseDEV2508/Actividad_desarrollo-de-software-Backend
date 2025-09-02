import { Device } from './device';

export interface Comment {
  id?: number;
  userName: string;
  commentText: string;
  rating: number;
  createdAt?: string;
  deviceId?: number;
  device?: Device;
}
