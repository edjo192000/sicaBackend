-- =====================================================
-- V4: Crear tablas de Materias
-- =====================================================

-- Tabla de Materias
CREATE TABLE subjects (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    professor_id VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_subjects_professor FOREIGN KEY (professor_id) REFERENCES persons(id)
);

CREATE INDEX idx_subjects_code ON subjects(code);
CREATE INDEX idx_subjects_professor ON subjects(professor_id);
CREATE INDEX idx_subjects_active ON subjects(active);

-- Tabla de Inscripciones a Materias
CREATE TABLE subject_enrollments (
    id VARCHAR(255) PRIMARY KEY,
    subject_id VARCHAR(255) NOT NULL,
    student_id VARCHAR(255) NOT NULL,
    enrolled_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_enrollment_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES students(person_id) ON DELETE CASCADE,
    CONSTRAINT uk_subject_student UNIQUE (subject_id, student_id)
);

CREATE INDEX idx_enrollments_subject ON subject_enrollments(subject_id);
CREATE INDEX idx_enrollments_student ON subject_enrollments(student_id);

-- Tabla de Horarios de Materias
CREATE TABLE subject_schedules (
    id VARCHAR(255) PRIMARY KEY,
    subject_id VARCHAR(255) NOT NULL,
    day_of_week VARCHAR(20) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    CONSTRAINT fk_schedules_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT chk_day_of_week CHECK (day_of_week IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'))
);

CREATE INDEX idx_schedules_subject ON subject_schedules(subject_id);
CREATE INDEX idx_schedules_day ON subject_schedules(day_of_week);
