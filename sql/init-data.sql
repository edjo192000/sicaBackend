-- Script para inicializar datos de prueba en SICA

-- Insertar Áreas
INSERT INTO areas (id, name, description, active) VALUES
('area-001', 'Sistemas', 'División de Sistemas y Computación', true),
('area-002', 'Mecatrónica', 'División de Mecatrónica', true),
('area-003', 'Administración', 'Área Administrativa', true);

-- Insertar Divisiones
INSERT INTO divisions (id, name, level, active) VALUES
('div-001', 'Sistemas y Computación', 'INGENIERIA', true),
('div-002', 'Mecatrónica', 'INGENIERIA', true),
('div-003', 'Informática', 'TSU', true);

-- Insertar Carreras
INSERT INTO careers (id, name, division_id, active) VALUES
('car-001', 'Ingeniería en Sistemas Computacionales', 'div-001', true),
('car-002', 'Ingeniería en Desarrollo de Software', 'div-001', true),
('car-003', 'Ingeniería en Mecatrónica', 'div-002', true),
('car-004', 'TSU en Desarrollo de Software Multiplataforma', 'div-003', true);

-- Insertar Personas (Administrador)
INSERT INTO persons (id, first_name, last_name, second_last_name, email, phone, type, active, created_at, updated_at) VALUES
('person-admin-001', 'Juan', 'García', 'López', 'admin@sica.edu.mx', '4491234567', 'ADMINISTRATIVO', true, NOW(), NOW());

-- Insertar Empleado (Administrador)
INSERT INTO employees (person_id, employee_number, area_id, shift, entry_time, exit_time) VALUES
('person-admin-001', 'EMP001', 'area-003', 'MATUTINO', '08:00', '16:00');

-- Insertar Usuario Administrador (password: admin123)
INSERT INTO users (id, username, password, role, person_id, active, created_at, updated_at) VALUES
('user-admin-001', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMINISTRADOR', 'person-admin-001', true, NOW(), NOW());

-- Insertar Personas (Profesores)
INSERT INTO persons (id, first_name, last_name, second_last_name, email, phone, type, active, created_at, updated_at) VALUES
('person-prof-001', 'María', 'Rodríguez', 'Sánchez', 'maria.rodriguez@sica.edu.mx', '4491234568', 'PROFESOR', true, NOW(), NOW()),
('person-prof-002', 'Carlos', 'Martínez', 'Hernández', 'carlos.martinez@sica.edu.mx', '4491234569', 'PROFESOR', true, NOW(), NOW());

-- Insertar Empleados (Profesores)
INSERT INTO employees (person_id, employee_number, area_id, shift, entry_time, exit_time) VALUES
('person-prof-001', 'PROF001', 'area-001', 'MATUTINO', '07:00', '15:00'),
('person-prof-002', 'PROF002', 'area-001', 'VESPERTINO', '14:00', '22:00');

-- Insertar Usuarios Profesores (password: prof123)
INSERT INTO users (id, username, password, role, person_id, active, created_at, updated_at) VALUES
('user-prof-001', 'maria.rodriguez', '$2a$10$8E8bE7kJ9wY6VkMQKF8zKuLXZ5HZqh0qF5YjF5Y5Y5Y5Y5Y5Y5Y5Y', 'PROFESOR', 'person-prof-001', true, NOW(), NOW()),
('user-prof-002', 'carlos.martinez', '$2a$10$8E8bE7kJ9wY6VkMQKF8zKuLXZ5HZqh0qF5YjF5Y5Y5Y5Y5Y5Y5Y5Y', 'PROFESOR', 'person-prof-002', true, NOW(), NOW());

-- Insertar Personas (Estudiantes)
INSERT INTO persons (id, first_name, last_name, second_last_name, email, phone, type, active, created_at, updated_at) VALUES
('person-est-001', 'Ana', 'González', 'Pérez', 'ana.gonzalez@alumnos.sica.edu.mx', '4491234570', 'ESTUDIANTE', true, NOW(), NOW()),
('person-est-002', 'Luis', 'Ramírez', 'Torres', 'luis.ramirez@alumnos.sica.edu.mx', '4491234571', 'ESTUDIANTE', true, NOW(), NOW()),
('person-est-003', 'Carmen', 'López', 'Flores', 'carmen.lopez@alumnos.sica.edu.mx', '4491234572', 'ESTUDIANTE', true, NOW(), NOW());

-- Insertar Estudiantes
INSERT INTO students (person_id, enrollment_number, level, division_id, career_id, shift) VALUES
('person-est-001', '20210001', 'INGENIERIA', 'div-001', 'car-001', 'MATUTINO'),
('person-est-002', '20210002', 'INGENIERIA', 'div-001', 'car-002', 'MATUTINO'),
('person-est-003', '20210003', 'TSU', 'div-003', 'car-004', 'VESPERTINO');

-- Insertar Usuarios Estudiantes (password: est123)
INSERT INTO users (id, username, password, role, person_id, active, created_at, updated_at) VALUES
('user-est-001', 'ana.gonzalez', '$2a$10$8E8bE7kJ9wY6VkMQKF8zKuLXZ5HZqh0qF5YjF5Y5Y5Y5Y5Y5Y5Y5Y', 'ESTUDIANTE', 'person-est-001', true, NOW(), NOW()),
('user-est-002', 'luis.ramirez', '$2a$10$8E8bE7kJ9wY6VkMQKF8zKuLXZ5HZqh0qF5YjF5Y5Y5Y5Y5Y5Y5Y5Y', 'ESTUDIANTE', 'person-est-002', true, NOW(), NOW()),
('user-est-003', 'carmen.lopez', '$2a$10$8E8bE7kJ9wY6VkMQKF8zKuLXZ5HZqh0qF5YjF5Y5Y5Y5Y5Y5Y5Y5Y', 'ESTUDIANTE', 'person-est-003', true, NOW(), NOW());

-- Insertar Materias
INSERT INTO subjects (id, name, code, professor_id, active, created_at) VALUES
('sub-001', 'Programación Web', 'PW-2024-1', 'person-prof-001', true, NOW()),
('sub-002', 'Base de Datos', 'BD-2024-1', 'person-prof-001', true, NOW()),
('sub-003', 'Redes de Computadoras', 'RC-2024-1', 'person-prof-002', true, NOW());

-- Inscribir estudiantes a materias
INSERT INTO subject_enrollments (id, subject_id, student_id, enrolled_at) VALUES
('enr-001', 'sub-001', 'person-est-001', NOW()),
('enr-002', 'sub-001', 'person-est-002', NOW()),
('enr-003', 'sub-002', 'person-est-001', NOW()),
('enr-004', 'sub-002', 'person-est-002', NOW()),
('enr-005', 'sub-003', 'person-est-001', NOW());

-- Mensaje final
SELECT 'Base de datos inicializada correctamente' as mensaje;
SELECT 'Usuario admin: admin / admin123' as credenciales_admin;
SELECT 'Profesores: maria.rodriguez / prof123, carlos.martinez / prof123' as credenciales_profesores;
SELECT 'Estudiantes: ana.gonzalez / est123, luis.ramirez / est123, carmen.lopez / est123' as credenciales_estudiantes;
