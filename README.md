## Smart Devices Web Application

## Descripción del Proyecto

**Smart Devices** es una pagina web para el catálogo de dispositivos inteligentes, Estructurando el Backend **Java Spring Boot** siguiendo el patrón de **Arquitectura por Capas** Visto en la leccion 1 del momento Contextualizacion.

### 🎯 Objetivos del Proyecto

- Crear un sitio web de dispositivos inteligentes con funcionalidades completas
- Implementar arquitectura por capas 
- Preparar la aplicación para futura implementación de microservicios
- Demostrar conocimientos de la arquitecura de Capas

## 🏗️ Arquitectura del Sistema

### Patrón de Arquitectura: **Layered Architecture (Arquitectura por Capas)**

```
┌─────────────────────────────────────┐
│           PRESENTATION LAYER        │  ← Controllers (REST API)
├─────────────────────────────────────┤
│           BUSINESS LAYER            │  ← Services (Business Logic)
├─────────────────────────────────────┤
│           DATA ACCESS LAYER         │  ← Repositories (Data Access)
├─────────────────────────────────────┤
│           MODEL LAYER               │  ← Entities (Data Models)
└─────────────────────────────────────┘
```

### Estructura de Capas:

1. **Presentation Layer (Capa de Presentación)**
   - Controllers que manejan las peticiones HTTP
   - Validación de entrada de datos

2. **Business Layer (Capa de Negocio)**
   - Services que contienen la lógica de negocio
   - Validaciones de negocio

3. **Data Access Layer (Capa de Acceso a Datos)**
   - Repositories para acceso a la base de datos
   - Consultas personalizadas
   - Manejo de datos

4. **Model Layer (Capa de Modelo)**
   - Entidades JPA
   - DTOs (Data Transfer Objects)
   - Enums y clases de dominio

## 🛠️ Tecnologías Utilizadas

### Back-end
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (Base de datos en memoria)


### Front-end
- **Bootstrap 5.3.0** (Framework CSS)
- **Font Awesome 6.0.0** (Iconos)
- **HTML5 & CSS3**
- **TypeScript** 

### Herramientas de Desarrollo
- **Spring Boot DevTools** (Desarrollo)
- **H2 Console** (Administración de BD)
- **Spring Boot Validation** (Validaciones)

## 📁 Estructura del Proyecto

```
├── src/
│   ├── main/
│   │   ├── java/com/smartdevices/
│   │   │   ├── SmartDevicesApplication.java
│   │   │   ├── controller/           # Presentation Layer
│   │   │   │   ├── DeviceController.java
│   │   │   │   ├── CommentController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── service/              # Business Layer
│   │   │   │   ├── DeviceService.java
│   │   │   │   └── CommentService.java
│   │   │   ├── repository/           # Data Access Layer
│   │   │   │   ├── DeviceRepository.java
│   │   │   │   └── CommentRepository.java
│   │   │   └── model/                # Model Layer
│   │   │       ├── Device.java
│   │   │       ├── Comment.java
│   │   │       └── DeviceType.java
│   │   └── resources/
│   │       ├── templates/            # Views (Thymeleaf)
│   │       │   ├── home.html
│   │       │   ├── devices/
│   │       │   │   ├── list.html
│   │       │   │   └── detail.html
│   │       │   └── admin/
│   │       │       ├── dashboard.html
│   │       │       └── device-form.html
│   │       ├── application.properties
│   │       └── data.sql              # Datos iniciales
├── pom.xml                          # Dependencias Maven
└── README.md                        # Documentación
```

## 🗄️ Modelo de Base de Datos

### Diagrama Entidad-Relación

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

### Entidades Principales:

**Device (Dispositivo)**
   - Información básica del dispositivo
   - Relación 1:N con Comments
   - Tipos: SMARTPHONE, LAPTOP, TABLET, SMARTWATCH, HEADPHONES, etc.

**Comment (Comentario)**
   - Reseñas de usuarios
   - Sistema de calificación (1-5 estrellas)
   - Relación N:1 con Device

## Funcionalidades Implementadas

### Funcionalidades Principales

1. **Página de Inicio**
   - Hero section con llamada a la acción
   - Categorías de dispositivos
   - Últimos lanzamientos
   - Características destacadas

2. **Catálogo de Dispositivos**
   - Listado con filtros por tipo y marca
   - Búsqueda de dispositivos
   - Ordenamiento por diferentes criterios
   - Paginación (preparado)

3. **Detalle de Dispositivo**
   - Información completa del producto
   - Especificaciones técnicas
   - Sistema de comentarios y calificaciones
   - Dispositivos relacionados

4. **Sistema de Comentarios**
   - Usuarios pueden agregar reseñas
   - Calificación con estrellas (1-5)
   - Validación de entrada
   - Promedio de calificaciones

5. **Panel de Administración**
   - Dashboard con estadísticas
   - CRUD completo de dispositivos
   - Gestión de comentarios
   - Interfaz intuitiva

6. **Búsqueda y Filtros**
   - Búsqueda por texto
   - Filtros por tipo de dispositivo
   - Filtros por marca
   - Filtros por rango de precio
   - Búsqueda avanzada


## Instalación y Configuración

### Pasos de Instalación

1. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

2. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

3. **Acceder a la aplicación**
   - **Aplicación principal**: http://localhost:8080
   - **Panel de administración**: http://localhost:8080/admin
   - **Consola H2**: http://localhost:8080/h2-console

### Configuración de Base de Datos

La aplicación utiliza H2 Database en modo archivo:

```properties
# Configuración en application.properties
spring.datasource.url=jdbc:h2:file:./data/smartdevices
spring.datasource.username=sa
spring.datasource.password=password
```

## 🔍 Endpoints de la API

### Dispositivos
- `GET /` - Página de inicio
- `GET /devices` - Listado de dispositivos
- `GET /devices/{id}` - Detalle de dispositivo
- `GET /devices/search?q={term}` - Búsqueda
- `GET /devices/type/{type}` - Filtro por tipo
- `GET /devices/brand/{brand}` - Filtro por marca
- `GET /devices/advanced-search` - Búsqueda avanzada

### Administración
- `GET /admin` - Dashboard de administración
- `GET /devices/admin/new` - Formulario nuevo dispositivo
- `POST /devices/admin/new` - Crear dispositivo
- `GET /devices/admin/edit/{id}` - Formulario editar
- `POST /devices/admin/edit/{id}` - Actualizar dispositivo
- `POST /devices/admin/delete/{id}` - Eliminar dispositivo

### Comentarios
- `POST /comments/device/{id}` - Agregar comentario
- `GET /comments/admin` - Listar comentarios (admin)
- `POST /comments/admin/delete/{id}` - Eliminar comentario


## Escalabilidad y Mantenibilidad

### Preparado para Microservicios
- **Layered Architecture**: Separación clara de responsabilidades
- **Dependency Injection**: Inyección de dependencias
- **Interface Segregation**: Interfaces bien definidas
- **Single Responsibility**: Principio de responsabilidad única

### Código Limpio
- **SOLID Principles**: Principios SOLID aplicados
- **Clean Code**: Código legible y mantenible
- **Documentation**: Comentarios y documentación
- **Consistent Naming**: Nomenclatura consistente


### Variables de Entorno
```bash
# Configuración de base de datos
SPRING_DATASOURCE_URL=jdbc:h2:file:./data/smartdevices
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=password

# Configuración del servidor
SERVER_PORT=8080
```

## Autor

**Nombre del Estudiante** Juan Sebastian Palacios Bautista
- **Email**: jsebastianpalacios@ucomepnsar.edu.co
- **Universidad**: Fundacion Universitaria Compensar 
- **Curso**: DESARROLLO DE SOFTWARE WEB BACK-END
- **Fecha**: 2025


# Actividad_desarrollo-de-software-Backend
