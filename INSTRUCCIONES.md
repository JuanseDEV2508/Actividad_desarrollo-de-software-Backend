# INSTRUCCIONES DEL PROYECTO SMART DEVICES

## RESUMEN DEL PROYECTO

## ARQUITECTURA IMPLEMENTADA

### Patrón: **Layered Architecture (Arquitectura por Capas)**

```
┌─────────────────────────────────────┐
│           PRESENTATION LAYER        │  ← Controllers + Thymeleaf
├─────────────────────────────────────┤
│           BUSINESS LAYER            │  ← Services (Lógica de Negocio)
├─────────────────────────────────────┤
│           DATA ACCESS LAYER         │  ← Repositories (Acceso a Datos)
├─────────────────────────────────────┤
│           MODEL LAYER               │  ← Entities (Modelos de Datos)
└─────────────────────────────────────┘
```

### Estructura de Capas:

1. **Presentation Layer (Capa de Presentación)**
   - `DeviceController.java` - Maneja peticiones HTTP de dispositivos
   - `CommentController.java` - Maneja peticiones HTTP de comentarios
   - `HomeController.java` - Maneja rutas principales
   - Templates Thymeleaf - Interfaz de usuario

2. **Business Layer (Capa de Negocio)**
   - `DeviceService.java` - Lógica de negocio para dispositivos
   - `CommentService.java` - Lógica de negocio para comentarios
   - Validaciones de negocio
   - Transacciones

3. **Data Access Layer (Capa de Acceso a Datos)**
   - `DeviceRepository.java` - Acceso a datos de dispositivos
   - `CommentRepository.java` - Acceso a datos de comentarios
   - Consultas personalizadas con JPA

4. **Model Layer (Capa de Modelo)**
   - `Device.java` - Entidad de dispositivo
   - `Comment.java` - Entidad de comentario
   - `DeviceType.java` - Enum de tipos de dispositivo

## 🗄️ MODELO DE BASE DE DATOS

### Diagrama Entidad-Relación:

```
┌─────────────────┐         ┌─────────────────┐
│     DEVICES     │         │    COMMENTS     │
├─────────────────┤         ├─────────────────┤
│ id (PK)         │◄────────┤ id (PK)         │
│ name            │         │ user_name       │
│ brand           │         │ comment_text    │
│ type            │         │ rating          │
│ description     │         │ created_at      │
│ price           │         │ device_id (FK)  │
│ release_date    │         └─────────────────┘
│ image_url       │
│ specifications  │
└─────────────────┘
```

### Entidades:

1. **Device (Dispositivo)**
   - Información básica del dispositivo
   - Relación 1:N con Comments
   - Tipos: SMARTPHONE, LAPTOP, TABLET, SMARTWATCH, HEADPHONES, etc.

2. **Comment (Comentario)**
   - Reseñas de usuarios
   - Sistema de calificación (1-5 estrellas)
   - Relación N:1 con Device

## CÓMO EJECUTAR EL PROYECTO

s

### Configuración de Base de Datos:
- **URL**: jdbc:h2:file:./data/smartdevices
- **Usuario**: sa
- **Contraseña**: password

## ENDPOINTS PRINCIPALES

### Dispositivos:
- `GET /` - Página de inicio
- `GET /devices` - Listado de dispositivos
- `GET /devices/{id}` - Detalle de dispositivo
- `GET /devices/search?q={term}` - Búsqueda
- `GET /devices/type/{type}` - Filtro por tipo
- `GET /devices/brand/{brand}` - Filtro por marca
- `GET /devices/advanced-search` - Búsqueda avanzada

### Administración:
- `GET /admin` - Dashboard de administración
- `GET /devices/admin/new` - Formulario nuevo dispositivo
- `POST /devices/admin/new` - Crear dispositivo
- `GET /devices/admin/edit/{id}` - Formulario editar
- `POST /devices/admin/edit/{id}` - Actualizar dispositivo
- `POST /devices/admin/delete/{id}` - Eliminar dispositivo

### Comentarios:
- `POST /comments/device/{id}` - Agregar comentario
- `GET /comments/admin` - Listar comentarios (admin)
- `POST /comments/admin/delete/{id}` - Eliminar comentario

### Entorno Local:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
mvn spring-boot:run
```

### JAR File:
```bash
mvn package
java -jar target/smart-devices-web-1.0.0.jar
```



