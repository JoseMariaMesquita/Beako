CREATE DATABASE IF NOT EXISTS beakobeta
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE beakobeta;

-- Tabla: colecciones
CREATE TABLE collections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    totalvolumes INT NOT NULL DEFAULT 0,
    owned INT NOT NULL DEFAULT 0,
    collectionstate ENUM('stopped', 'finished', 'onreading') NOT NULL,
    publishingstate ENUM('cancelled', 'finished', 'hiatus', 'ongoing') NOT NULL
);

-- Tabla: libros
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    volumenumber INT NOT NULL,
    editorial VARCHAR(255) NOT NULL,
    language VARCHAR(3) NOT NULL,
    bookstate ENUM('stopped', 'finished', 'onreading') NOT NULL,
    collection INT NOT NULL,
    CONSTRAINT fk_libros_coleccion
        FOREIGN KEY (collection) REFERENCES collections(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT chk_lenguage_mayusculas
        CHECK (language = BINARY UPPER(language))
);