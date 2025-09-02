# Smart Devices Frontend - Angular

Este es el frontend de la aplicación Smart Devices desarrollado con Angular 17.

## 🚀 Características

- **Angular 17**: Framework moderno para aplicaciones web
- **Bootstrap 5**: Framework CSS para diseño responsive
- **Font Awesome**: Iconos profesionales
- **TypeScript**: Tipado estático para mejor desarrollo
- **RxJS**: Programación reactiva
- **Angular Router**: Navegación entre componentes
- **Angular Forms**: Manejo de formularios

## 📋 Prerrequisitos

- Node.js (versión 18 o superior)
- npm (incluido con Node.js)
- Angular CLI

## 🛠️ Instalación

1. **Instalar Angular CLI globalmente:**
   ```bash
   npm install -g @angular/cli
   ```

2. **Instalar dependencias:**
   ```bash
   npm install
   ```

3. **Ejecutar la aplicación:**
   ```bash
   ng serve
   ```

4. **Abrir en el navegador:**
   ```
   http://localhost:4200
   ```

## 🏗️ Estructura del Proyecto

```
src/
├── app/
│   ├── components/
│   │   ├── home/                 # Página de inicio
│   │   ├── device-list/          # Lista de dispositivos
│   │   ├── device-detail/        # Detalle de dispositivo
│   │   └── admin/                # Componentes de administración
│   ├── models/                   # Interfaces TypeScript
│   ├── services/                 # Servicios para API
│   ├── app.component.*           # Componente principal
│   ├── app.config.ts            # Configuración de la app
│   └── app.routes.ts            # Rutas de la aplicación
├── assets/                       # Recursos estáticos
└── styles.css                    # Estilos globales
```

## 🔧 Configuración

### Variables de Entorno

El frontend se conecta al backend Spring Boot en `http://localhost:8080`. Asegúrate de que el backend esté ejecutándose.

### CORS

El backend está configurado para permitir peticiones desde `http://localhost:4200`.

## 📱 Componentes Principales

### Home Component
- Hero section con llamadas a la acción
- Categorías de productos
- Últimos lanzamientos
- Características de la empresa

### Device List Component
- Lista de dispositivos con filtros
- Búsqueda por nombre, marca, tipo
- Ordenamiento por precio, fecha
- Paginación

### Device Detail Component
- Información detallada del dispositivo
- Galería de imágenes
- Especificaciones técnicas
- Sistema de comentarios y calificaciones

### Admin Components
- Dashboard administrativo
- Formularios para crear/editar dispositivos
- Gestión de comentarios

## 🎨 Diseño

### Bootstrap 5
- Sistema de grid responsive
- Componentes predefinidos
- Utilidades CSS
- Temas personalizables

### Font Awesome
- Iconos vectoriales
- Escalables sin pérdida de calidad
- Amplia biblioteca de iconos

## 🔌 Servicios

### DeviceService
- `getAllDevices()`: Obtener todos los dispositivos
- `getDeviceById(id)`: Obtener dispositivo por ID
- `getDevicesByType(type)`: Filtrar por tipo
- `searchDevices(term)`: Búsqueda de dispositivos
- `getLatestDevices()`: Últimos lanzamientos

### CommentService
- `getCommentsByDeviceId(deviceId)`: Comentarios de un dispositivo
- `addComment(deviceId, comment)`: Agregar comentario
- `getAverageRatingByDeviceId(deviceId)`: Calificación promedio

## 🚀 Comandos Útiles

```bash
# Desarrollo
ng serve                    # Servidor de desarrollo
ng serve --port 4201       # Puerto específico

# Construcción
ng build                   # Construcción para producción
ng build --configuration production

# Testing
ng test                    # Ejecutar tests unitarios
ng e2e                     # Ejecutar tests end-to-end

# Generación de código
ng generate component      # Generar componente
ng generate service        # Generar servicio
ng generate pipe           # Generar pipe
```

## 📦 Dependencias Principales

```json
{
  "@angular/core": "^17.0.0",
  "@angular/common": "^17.0.0",
  "@angular/router": "^17.0.0",
  "@angular/forms": "^17.0.0",
  "bootstrap": "^5.3.0",
  "@fortawesome/fontawesome-free": "^6.0.0",
  "@popperjs/core": "^2.11.0"
}
```

## 🔒 Seguridad

- Validación de formularios en el cliente
- Sanitización de datos
- Protección contra XSS
- Validación de tipos con TypeScript

## 📱 Responsive Design

La aplicación está diseñada para funcionar en:
- **Desktop**: 1200px+
- **Tablet**: 768px - 1199px
- **Mobile**: < 768px

## 🧪 Testing

```bash
# Tests unitarios
ng test

# Tests end-to-end
ng e2e

# Coverage
ng test --code-coverage
```

## 📈 Performance

- Lazy loading de módulos
- Optimización de imágenes
- Minificación de código
- Tree shaking
- Service workers (opcional)

## 🚀 Deployment

### Build para Producción
```bash
ng build --configuration production
```

### Servidor Web
Los archivos generados en `dist/` pueden ser servidos por cualquier servidor web estático.

### Netlify/Vercel
Configurar el directorio de build como `dist/smart-devices-frontend`.

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 👨‍💻 Autor

Desarrollado como parte del proyecto Smart Devices.

---

**¡Gracias por usar Smart Devices Frontend! 🚀**
