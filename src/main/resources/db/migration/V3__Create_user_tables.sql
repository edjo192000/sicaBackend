-- =====================================================
-- V3: Crear tabla de Usuarios
-- =====================================================

-- Tabla de Usuarios
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    person_id VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    last_login TIMESTAMP,
    CONSTRAINT fk_users_person FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE,
    CONSTRAINT chk_user_role CHECK (role IN ('ADMINISTRADOR', 'PROFESOR', 'ESTUDIANTE'))
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_person ON users(person_id);
CREATE INDEX idx_users_active ON users(active);
