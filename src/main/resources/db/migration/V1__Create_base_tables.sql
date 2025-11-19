-- =====================================================
-- V1: Crear tablas base (Áreas, Divisiones, Carreras)
-- =====================================================

-- Tabla de Áreas
CREATE TABLE areas (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE INDEX idx_areas_active ON areas(active);

-- Tabla de Divisiones
CREATE TABLE divisions (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT chk_division_level CHECK (level IN ('INGENIERIA', 'TSU'))
);

CREATE INDEX idx_divisions_level ON divisions(level);
CREATE INDEX idx_divisions_active ON divisions(active);

-- Tabla de Carreras
CREATE TABLE careers (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    division_id VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT fk_careers_division FOREIGN KEY (division_id) REFERENCES divisions(id)
);

CREATE INDEX idx_careers_division ON careers(division_id);
CREATE INDEX idx_careers_active ON careers(active);
