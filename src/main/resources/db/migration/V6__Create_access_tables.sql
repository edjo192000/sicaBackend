-- =====================================================
-- V6: Crear tablas de Acceso y Visitas
-- =====================================================

-- Tabla de Registros de Acceso
CREATE TABLE access_records (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    device VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    method VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_access_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT chk_access_type CHECK (type IN ('ENTRADA', 'SALIDA')),
    CONSTRAINT chk_access_method CHECK (method IN ('HUELLA', 'TARJETA'))
);

CREATE INDEX idx_access_user ON access_records(user_id);
CREATE INDEX idx_access_date ON access_records(date);
CREATE INDEX idx_access_type ON access_records(type);
CREATE INDEX idx_access_datetime ON access_records(date, time);

-- Tabla de Visitas
CREATE TABLE visits (
    id VARCHAR(255) PRIMARY KEY,
    visitor_name VARCHAR(255) NOT NULL,
    visit_datetime TIMESTAMP NOT NULL,
    person_visited VARCHAR(255) NOT NULL,
    visitor_photo TEXT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    authorized_by VARCHAR(255),
    authorized_at TIMESTAMP,
    qr_code_base64 TEXT,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT chk_visit_status CHECK (status IN ('PENDING', 'AUTORIZADO'))
);

CREATE INDEX idx_visit_status ON visits(status);
CREATE INDEX idx_visit_datetime ON visits(visit_datetime);
CREATE INDEX idx_visit_name ON visits(visitor_name);
