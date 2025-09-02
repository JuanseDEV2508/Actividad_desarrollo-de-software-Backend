import { Comment } from './comment';

export interface Device {
  id?: number;
  name: string;
  brand: string;
  type: DeviceType;
  description?: string;
  price: number;
  releaseDate?: string;
  imageUrl?: string;
  specifications?: string;
  comments?: Comment[];
}

export enum DeviceType {
  SMARTPHONE = 'SMARTPHONE',
  LAPTOP = 'LAPTOP',
  TABLET = 'TABLET',
  SMARTWATCH = 'SMARTWATCH',
  HEADPHONES = 'HEADPHONES',
  SMART_SPEAKER = 'SMART_SPEAKER',
  CAMERA = 'CAMERA',
  GAMING_CONSOLE = 'GAMING_CONSOLE'
}

export const DeviceTypeLabels: Record<DeviceType, string> = {
  [DeviceType.SMARTPHONE]: 'Smartphone',
  [DeviceType.LAPTOP]: 'Laptop',
  [DeviceType.TABLET]: 'Tablet',
  [DeviceType.SMARTWATCH]: 'Smartwatch',
  [DeviceType.HEADPHONES]: 'Headphones',
  [DeviceType.SMART_SPEAKER]: 'Smart Speaker',
  [DeviceType.CAMERA]: 'Camera',
  [DeviceType.GAMING_CONSOLE]: 'Gaming Console'
};
