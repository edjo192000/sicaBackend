-- =====================================================
-- V2: Crear tablas de Personas (Persons, Students, Employees)
-- =====================================================

-- Tabla de Personas
CREATE TABLE persons (
    id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    second_last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    photo_url TEXT,
    type VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT chk_person_type CHECK (type IN ('ESTUDIANTE', 'PROFESOR', 'ADMINISTRATIVO', 'DIRECTIVO'))
);

CREATE INDEX idx_persons_email ON persons(email);
CREATE INDEX idx_persons_type ON persons(type);
CREATE INDEX idx_persons_active ON persons(active);

-- Tabla de Estudiantes
CREATE TABLE students (
    person_id VARCHAR(255) PRIMARY KEY,
    enrollment_number VARCHAR(50) NOT NULL UNIQUE,
    level VARCHAR(50) NOT NULL,
    division_id VARCHAR(255) NOT NULL,
    career_id VARCHAR(255) NOT NULL,
    shift VARCHAR(50) NOT NULL,
    CONSTRAINT fk_students_person FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE,
    CONSTRAINT fk_students_division FOREIGN KEY (division_id) REFERENCES divisions(id),
    CONSTRAINT fk_students_career FOREIGN KEY (career_id) REFERENCES careers(id),
    CONSTRAINT chk_student_level CHECK (level IN ('INGENIERIA', 'TSU')),
    CONSTRAINT chk_student_shift CHECK (shift IN ('MATUTINO', 'VESPERTINO'))
);

CREATE INDEX idx_students_enrollment ON students(enrollment_number);
CREATE INDEX idx_students_division ON students(division_id);
CREATE INDEX idx_students_career ON students(career_id);

-- Tabla de Empleados
CREATE TABLE employees (
    person_id VARCHAR(255) PRIMARY KEY,
    employee_number VARCHAR(50) NOT NULL UNIQUE,
    area_id VARCHAR(255) NOT NULL,
    shift VARCHAR(50) NOT NULL,
    entry_time TIME NOT NULL,
    exit_time TIME NOT NULL,
    CONSTRAINT fk_employees_person FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE,
    CONSTRAINT fk_employees_area FOREIGN KEY (area_id) REFERENCES areas(id),
    CONSTRAINT chk_employee_shift CHECK (shift IN ('MATUTINO', 'VESPERTINO'))
);

CREATE INDEX idx_employees_number ON employees(employee_number);
CREATE INDEX idx_employees_area ON employees(area_id);
