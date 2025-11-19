# SICA Backend - Sistema Integral de Control de Acceso

Backend desarrollado con Spring Boot 3.5.7, Java 21, PostgreSQL y Redis para el Sistema Integral de Control de Acceso (SICA).

## 🚀 Tecnologías

- **Java**: 21
- **Spring Boot**: 3.5.7
- **Build Tool**: Gradle (Kotlin DSL)
- **Base de Datos**: PostgreSQL
- **Migraciones**: Flyway
- **Cache**: Redis
- **Seguridad**: Spring Security + JWT
- **ORM**: JPA/Hibernate
- **Generación QR**: ZXing

## 📋 Requisitos Previos

- JDK 21 o superior
- PostgreSQL 14 o superior
- Redis 6 o superior
- Gradle 8.x (incluido wrapper)

## 🛠️ Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone <repository-url>
cd sicaBackend
```

### 2. Configurar Base de Datos PostgreSQL

Crear la base de datos:

```sql
CREATE DATABASE sica_db;
CREATE USER sica_user WITH ENCRYPTED PASSWORD 'sica_password';
GRANT ALL PRIVILEGES ON DATABASE sica_db TO sica_user;
```

**Nota**: Las tablas se crearán automáticamente mediante Flyway al iniciar la aplicación por primera vez. No es necesario ejecutar scripts SQL manualmente.

### 3. Configurar Redis

Asegurarse de que Redis esté corriendo:

```bash
# Linux/Mac
redis-server

# Con Docker
docker run -d -p 6379:6379 redis:alpine
```

### 4. Configurar Variables de Entorno

Crear un archivo `.env` en la raíz del proyecto o configurar las variables de entorno:

```bash
# Base de Datos
DB_HOST=localhost
DB_PORT=5432
DB_NAME=sica_db
DB_USERNAME=sica_user
DB_PASSWORD=sica_password

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=

# JWT
JWT_SECRET=tu-clave-secreta-super-segura-aqui
JWT_EXPIRATION=86400000
JWT_REFRESH_EXPIRATION=604800000

# Archivo
UPLOAD_DIR=./uploads

# CORS
CORS_ORIGINS=http://localhost:3000,http://localhost:3001

# Logs
LOG_LEVEL=DEBUG
```

### 5. Compilar y Ejecutar

```bash
# Compilar el proyecto
./gradlew build

# Ejecutar el proyecto
./gradlew bootRun

# O usando el JAR generado
java -jar build/libs/sica-backend-1.0.0.jar
```

La aplicación estará disponible en: `http://localhost:8080/api`

## 📁 Estructura del Proyecto

```
src/main/kotlin/com/sica/backend/
├── config/                 # Configuraciones (Security, Redis, CORS)
│   ├── SecurityConfig.kt
│   ├── RedisConfig.kt
│   └── CorsConfig.kt
├── controller/             # Controladores REST
│   ├── AuthController.kt
│   └── FileUploadController.kt
├── dto/                    # Data Transfer Objects
│   ├── AuthRequest.kt
│   ├── AuthResponse.kt
│   └── ...
├── entity/                 # Entidades JPA
│   ├── User.kt
│   ├── Person.kt
│   ├── Student.kt
│   ├── Employee.kt
│   ├── Subject.kt
│   ├── Attendance.kt
│   ├── Justification.kt
│   ├── Visit.kt
│   └── AccessRecord.kt
├── exception/              # Excepciones personalizadas
│   ├── ResourceNotFoundException.kt
│   ├── BadRequestException.kt
│   ├── UnauthorizedException.kt
│   └── GlobalExceptionHandler.kt
├── repository/             # Repositorios JPA
│   ├── UserRepository.kt
│   ├── PersonRepository.kt
│   └── ...
├── security/               # Seguridad y JWT
│   ├── JwtTokenProvider.kt
│   ├── JwtAuthenticationFilter.kt
│   ├── UserPrincipal.kt
│   └── CustomUserDetailsService.kt
├── service/                # Servicios de negocio
│   └── AuthService.kt
├── util/                   # Utilidades
│   ├── QRCodeGenerator.kt
│   └── FileStorageService.kt
└── SicaBackendApplication.kt
```

## 🔐 Autenticación

El sistema utiliza JWT (JSON Web Tokens) para la autenticación.

### Login

**POST** `/api/login`

```json
{
  "username": "admin",
  "password": "123456"
}
```

**Response:**

```json
{
  "id": "uuid",
  "username": "admin",
  "name": "Administrador Sistema",
  "role": "ADMINISTRADOR",
  "email": "admin@sica.com",
  "foto": null,
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
}
```

### Uso del Token

Incluir el token en el header de las peticiones:

```
Authorization: Bearer <token>
```

## 🗄️ Modelo de Datos

### Roles del Sistema

- **ADMINISTRADOR**: Acceso total al sistema
- **PROFESOR**: Gestión de asistencias y justificantes
- **ESTUDIANTE**: Consulta de asistencias y envío de justificantes

### Entidades Principales

- **User**: Usuarios del sistema
- **Person**: Información personal
- **Student**: Estudiantes (extiende Person)
- **Employee**: Empleados (extiende Person)
- **Subject**: Materias
- **Attendance**: Asistencias
- **Justification**: Justificantes
- **Visit**: Visitas
- **AccessRecord**: Registros de acceso

## 🔄 Cache con Redis

El sistema utiliza Redis para cachear:

- **users**: Información de usuarios (TTL: 30 min)
- **roles**: Roles y permisos (TTL: 24 horas)
- **subjects**: Materias (TTL: 15 min)
- **attendances**: Asistencias (TTL: 5 min)
- **justifications**: Justificantes (TTL: 5 min)

## 🔄 Migraciones con Flyway

El proyecto utiliza **Flyway** para gestionar las migraciones de base de datos de forma automática y versionada.

### Características

- **Migraciones automáticas**: Al iniciar la aplicación, Flyway ejecuta automáticamente las migraciones pendientes
- **Versionado**: Cada migración tiene un número de versión (V1, V2, V3, etc.)
- **Historial**: Flyway mantiene un registro de todas las migraciones ejecutadas en la tabla `flyway_schema_history`
- **Validación**: Verifica que las migraciones no hayan sido modificadas después de ejecutarse

### Estructura de Migraciones

Las migraciones se encuentran en: `src/main/resources/db/migration/`

```
db/migration/
├── V1__Create_base_tables.sql           # Áreas, Divisiones, Carreras
├── V2__Create_person_tables.sql         # Personas, Estudiantes, Empleados
├── V3__Create_user_tables.sql           # Usuarios
├── V4__Create_subject_tables.sql        # Materias e Inscripciones
├── V5__Create_attendance_tables.sql     # Asistencias y Justificantes
├── V6__Create_access_tables.sql         # Registros de Acceso y Visitas
└── V7__Insert_initial_data.sql          # Datos iniciales de prueba
```

### Datos Iniciales

La migración `V7__Insert_initial_data.sql` crea usuarios de prueba:

- **Administrador**: `admin` / `admin123`
- **Profesores**: `maria.rodriguez` / `prof123`, `carlos.martinez` / `prof123`
- **Estudiantes**: `ana.gonzalez` / `est123`, `luis.ramirez` / `est123`, `carmen.lopez` / `est123`

### Comandos Útiles

```bash
# Ver estado de las migraciones
./gradlew flywayInfo

# Validar migraciones
./gradlew flywayValidate

# Reparar checksums (usar con cuidado)
./gradlew flywayRepair

# Ver historial de migraciones (en PostgreSQL)
psql -U sica_user -d sica_db -c "SELECT * FROM flyway_schema_history;"
```

### Crear Nueva Migración

Para crear una nueva migración:

1. Crear archivo en `src/main/resources/db/migration/`
2. Nombrar como: `V{VERSION}__{DESCRIPCION}.sql` (ej: `V8__Add_email_verification.sql`)
3. Escribir el SQL de la migración
4. Al reiniciar la aplicación, Flyway ejecutará automáticamente la nueva migración

**Importante**:
- Nunca modificar migraciones que ya se ejecutaron
- Siempre crear nuevas migraciones para cambios adicionales
- El número de versión debe ser consecutivo

## 📤 Subida de Archivos

**POST** `/api/upload`

Content-Type: `multipart/form-data`

```bash
curl -X POST \
  http://localhost:8080/api/upload \
  -H 'Authorization: Bearer <token>' \
  -F 'file=@/path/to/file.pdf'
```

**Response:**

```json
{
  "success": true,
  "url": "/uploads/uuid.pdf",
  "fileUrl": "/uploads/uuid.pdf"
}
```

## 🏗️ Endpoints Principales a Implementar

### Personas

- `GET /api/persons` - Listar personas
- `POST /api/persons` - Crear persona
- `GET /api/persons/{id}` - Obtener persona
- `PUT /api/persons/{id}` - Actualizar persona
- `DELETE /api/persons/{id}` - Eliminar persona

### Materias

- `GET /api/materias/profesor/{profesorId}` - Materias del profesor
- `GET /api/materias/estudiante/{estudianteId}` - Materias del estudiante
- `GET /api/materias/{materiaId}/estudiantes` - Estudiantes de una materia

### Asistencias

- `POST /api/asistencias` - Registrar asistencias
- `GET /api/asistencias/estudiante/{estudianteId}` - Asistencias del estudiante
- `GET /api/asistencias/estudiante/{estudianteId}/faltas-sin-justificar` - Faltas sin justificar
- `GET /api/asistencias/materia/{materiaId}` - Asistencias de una materia
- `PUT /api/asistencias/{id}` - Actualizar asistencia

### Justificantes

- `POST /api/justificantes` - Crear justificante
- `GET /api/justificantes/estudiante/{estudianteId}` - Justificantes del estudiante
- `GET /api/justificantes/profesor/{profesorId}` - Justificantes del profesor
- `PUT /api/justificantes/{id}/aprobar` - Aprobar justificante
- `PUT /api/justificantes/{id}/rechazar` - Rechazar justificante

### Visitas

- `GET /api/visits` - Listar visitas
- `POST /api/visits` - Registrar visita
- `PUT /api/visits/{id}/authorize` - Autorizar visita

### Registros de Acceso

- `GET /api/attendance-records` - Listar registros
- `GET /api/v1/incidents/absences/person/{personId}` - Reporte de ausencias
- `GET /api/v1/incidents/tardiness/person/{personId}` - Reporte de retardos

## 🧪 Testing

```bash
# Ejecutar tests
./gradlew test

# Ejecutar tests con reporte
./gradlew test jacocoTestReport
```

## 📦 Build para Producción

```bash
# Generar JAR
./gradlew clean build -x test

# El JAR estará en: build/libs/sica-backend-1.0.0.jar
```

## 🐳 Docker

Crear archivo `Dockerfile`:

```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/sica-backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Construir y ejecutar:

```bash
docker build -t sica-backend .
docker run -p 8080:8080 sica-backend
```

## 🔧 Próximos Pasos de Desarrollo

### Tareas Pendientes

1. **Implementar servicios restantes**
   - PersonService
   - SubjectService
   - AttendanceService
   - JustificationService
   - VisitService
   - AccessRecordService

2. **Implementar controladores restantes**
   - PersonController
   - SubjectController
   - AttendanceController
   - JustificationController
   - VisitController
   - AccessRecordController

3. **Agregar validaciones de negocio**
   - Validar fechas de asistencia
   - Validar plazos de justificantes (3 días)
   - Validar permisos por rol

4. **Implementar paginación**
   - Usar Pageable en listados grandes

5. **Agregar documentación API**
   - Integrar Swagger/OpenAPI

6. **Implementar auditoría**
   - Logs de operaciones críticas

7. **Agregar tests**
   - Tests unitarios
   - Tests de integración

## 📝 Crear Usuario Administrador Inicial

Ejecutar este script SQL después de que la aplicación cree las tablas:

```sql
-- Insertar área administrativa
INSERT INTO areas (id, name, description, active) 
VALUES ('area-admin-001', 'Administración', 'Área administrativa', true);

-- Insertar persona administrador
INSERT INTO persons (id, first_name, last_name, second_last_name, email, type, active, created_at, updated_at)
VALUES ('person-admin-001', 'Admin', 'Sistema', null, 'admin@sica.com', 'ADMINISTRATIVO', true, NOW(), NOW());

-- Insertar empleado
INSERT INTO employees (person_id, employee_number, area_id, shift, entry_time, exit_time)
VALUES ('person-admin-001', 'EMP001', 'area-admin-001', 'MATUTINO', '08:00', '16:00');

-- Insertar usuario (password: admin123 - BCrypt hash)
INSERT INTO users (id, username, password, role, person_id, active, created_at, updated_at)
VALUES ('user-admin-001', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMINISTRADOR', 'person-admin-001', true, NOW(), NOW());
```

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit los cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

[Especificar licencia]

## 👥 Autores

- SICA Development Team

## 📞 Soporte

Para soporte, enviar email a: support@sica.com

---

**Última actualización**: 2025-11-19
**Versión**: 1.0.0
