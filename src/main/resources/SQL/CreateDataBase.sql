CREATE DATABASE IF NOT EXISTS beakobeta
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE beakobeta;

-- Tabla: colecciones
CREATE TABLE colecciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    totalvolumenes INT NOT NULL DEFAULT 0,
    totalposeidos INT NOT NULL DEFAULT 0,
    estadocoleccion ENUM('stopped', 'finished', 'onreading') NOT NULL,
    estadopublicacion ENUM('cancelado', 'terminado', 'hiatus', 'ongoing') NOT NULL
);

-- Tabla: libros
CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numerovolumen INT NOT NULL,
    editorial VARCHAR(255) NOT NULL,
    lenguage VARCHAR(3) NOT NULL,
    estadolibro ENUM('stopped', 'finished', 'onreading') NOT NULL,
    coleccion INT NOT NULL,
    CONSTRAINT fk_libros_coleccion
        FOREIGN KEY (coleccion) REFERENCES colecciones(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT chk_lenguage_mayusculas
        CHECK (lenguage = BINARY UPPER(lenguage))
);