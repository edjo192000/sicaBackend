-- =====================================================
-- V5: Crear tablas de Asistencias y Justificantes
-- =====================================================

-- Tabla de Asistencias
CREATE TABLE attendances (
    id VARCHAR(255) PRIMARY KEY,
    student_id VARCHAR(255) NOT NULL,
    subject_id VARCHAR(255) NOT NULL,
    professor_id VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    justified BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_attendances_student FOREIGN KEY (student_id) REFERENCES students(person_id) ON DELETE CASCADE,
    CONSTRAINT fk_attendances_subject FOREIGN KEY (subject_id) REFERENCES subjects(id),
    CONSTRAINT fk_attendances_professor FOREIGN KEY (professor_id) REFERENCES persons(id),
    CONSTRAINT chk_attendance_status CHECK (status IN ('PRESENTE', 'FALTA', 'RETARDO')),
    CONSTRAINT uk_attendance_student_subject_date UNIQUE (student_id, subject_id, date)
);

CREATE INDEX idx_attendance_student ON attendances(student_id);
CREATE INDEX idx_attendance_subject ON attendances(subject_id);
CREATE INDEX idx_attendance_date ON attendances(date);
CREATE INDEX idx_attendance_status ON attendances(status);
CREATE INDEX idx_attendance_justified ON attendances(justified);

-- Tabla de Justificantes
CREATE TABLE justifications (
    id VARCHAR(255) PRIMARY KEY,
    attendance_id VARCHAR(255) NOT NULL UNIQUE,
    student_id VARCHAR(255) NOT NULL,
    professor_id VARCHAR(255) NOT NULL,
    subject_id VARCHAR(255) NOT NULL,
    file_url TEXT NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    professor_comment TEXT,
    created_at TIMESTAMP NOT NULL,
    reviewed_at TIMESTAMP,
    CONSTRAINT fk_justifications_attendance FOREIGN KEY (attendance_id) REFERENCES attendances(id) ON DELETE CASCADE,
    CONSTRAINT fk_justifications_student FOREIGN KEY (student_id) REFERENCES students(person_id) ON DELETE CASCADE,
    CONSTRAINT fk_justifications_professor FOREIGN KEY (professor_id) REFERENCES persons(id),
    CONSTRAINT fk_justifications_subject FOREIGN KEY (subject_id) REFERENCES subjects(id),
    CONSTRAINT chk_justification_file_type CHECK (file_type IN ('IMAGEN', 'PDF')),
    CONSTRAINT chk_justification_status CHECK (status IN ('PENDIENTE', 'APROBADO', 'RECHAZADO'))
);

CREATE INDEX idx_justification_student ON justifications(student_id);
CREATE INDEX idx_justification_status ON justifications(status);
CREATE INDEX idx_justification_attendance ON justifications(attendance_id);
CREATE INDEX idx_justification_professor ON justifications(professor_id);
