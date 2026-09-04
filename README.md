# BeakoBeta

A desktop application for managing personal book (manga) collections. It lets you register the **collections** you own and then track which specific **volumes (books)** of each collection you actually have, storing everything in a MariaDB database.

## Overview

BeakoBeta is a Java Swing desktop app with a simple layered architecture:

- A **main window** shows a control panel on the left with buttons for managing books and collections, and an overview table on the right listing every collection with its key stats.
- From the main window you can **create, edit and delete** both *collections* and *books*.
- Double-clicking a collection row opens a **detail view** with stat cards and the list of books belonging to that collection.

## Features

- **Collections CRUD**
  - Create a collection with title, author, total number of volumes, collection status and publication status.
  - Edit an existing collection (statuses, totals, author).
  - Delete a collection. Deleting a collection also removes its books (via `ON DELETE CASCADE`).
  - See all collections in an overview table (title, author, total volumes, owned volumes, collection and publication state).
- **Books (volumes) CRUD**
  - Add a volume to an existing collection (volume number, publisher, language, condition). Adding a book automatically increments the collection's *owned volumes* counter.
  - Edit a book by looking it up by its DB id.
  - Delete a book.
- **Collection detail view**
  - Opened by double-clicking a collection: shows stat cards (total volumes, owned, collection status, publication status) plus a table of books, **filtered to the selected collection**.
- **Branding**
  - Icons for every action button in the control panel and a custom window icon.

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java (compiled for Java 25) |
| GUI | Swing (`javax.swing`) |
| Build tool | Maven |
| Database | MariaDB (via `mariadb-java-client` 3.5.3) |
| DB access | Plain JDBC + `PreparedStatement` |

## Requirements

- JDK 25 (set in `pom.xml` via `maven.compiler.source/target`)
- Apache Maven
- A running MariaDB server on `localhost:3306`
- The MariaDB JDBC driver is fetched automatically by Maven

## Database Setup

A ready-to-run SQL script is included in the repository:

```
src/main/resources/SQL/CreateDataBase.sql
```

It creates the database `beakobeta` and both tables. Run it against your MariaDB server (e.g. from the MySQL/MariaDB client):

```sql
SOURCE src/main/resources/SQL/CreateDataBase.sql;
```

The script defines:

```sql
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
```

> Note: the `lenguage` column is limited to 3 characters and must be uppercase (e.g. `ESP`, `ENG`), enforced by a check constraint.

## Configuration

Database connection settings are hard-coded in `src/main/java/config/ConfigDB.java`:

```java
private static final String URL   = "jdbc:mariadb://localhost:3306/beakobeta";
private static final String USR   = "root";
private static final String PASSWD = "";
```



## Build & Run

```bash
mvn compile          # compile the project
```

The app's entry point is the `Main` class (`org.example.Main`). Run it from your IDE (IntelliJ IDEA recommended): open the project, wait for Maven to import, then run `org.example.Main`.

> **Note:** the `main()` method in `Main.java` is declared as `static void main()` (package-private, no parameters). IDEs adapted to run it, but a standard `java -cp ... org.example.Main` launcher will not recognize it as an entry point.

The GUI loads its icons from `src/main/resources/icons/` using **relative paths**, so the app finds them as long as it is launched from the project root.

## Project Structure

```
BeakoBeta
├── pom.xml                          # Maven config (Java 25, MariaDB driver)
└── src/main
    ├── java
    │   ├── org/example/Main.java    # Entry point
    │   ├── config/ConfigDB.java     # DB connection (open/close JDBC)
    │   ├── dao/
    │   │   ├── ColeccionesDAO.java  # CRUD + query for collections
    │   │   └── LibroDAO.java        # CRUD + queries for books
    │   ├── entity/
    │   │   ├── Colecciones.java     # Collection model
    │   │   └── Libro.java           # Book model
    │   ├── exceptions/DBException.java  # Custom exception for DB errors
    │   ├── Utils/
    │   │   ├── CustomButton.java    # Styled JButton used in the control panel
    │   │   └── ImageResizer.java    # Resizes button icons
    │   └── view/
    │       ├── VentanaPrincipal.java    # Main window (control panel + overview)
    │       ├── CrearLibro.java          # "Create book" dialog
    │       ├── EditarLibro.java         # "Edit book" dialog
    │       ├── DeleteBook.java          # "Delete book" dialog
    │       ├── CrearColeccion.java      # "Create collection" dialog
    │       ├── EditarColeccion.java     # "Edit collection" dialog
    │       ├── DeleteCollection.java    # "Delete collection" dialog
    │       └── CollectionContents.java  # Collection detail view (stats + books)
    └── resources
        ├── icons/                  # Button / window icons (PNG)
        │   ├── beako.png
        │   ├── createBook.png, editBook.png, deleteBook.png
        │   └── createCollection.png, editCollection.png, deleteCollection.png
        └── SQL/
            └── CreateDataBase.sql  # DB schema setup script
```

## Architecture

- **`entity/`** — Plain Java objects (`Colecciones`, `Libro`) that carry the data.
- **`dao/`** — Persistence layer. Each DAO opens a connection, runs a `PreparedStatement`, closes the connection, and throws a `DBException` on any error. No ORM is used.
- **`config/ConfigDB.java`** — Central place for opening/closing JDBC connections.
- **`view/`** — All Swing windows. Each dialog is a `JFrame` opened from the main window.
- **`exceptions/DBException.java`** — Custom checked exception used throughout the app so the UI can show DB errors in message dialogs.


## Usage Guide

1. **Main window**: the left panel holds the action buttons (with icons); the right side shows the collection overview table.
2. **Create a collection**: click *Crear Colección*, fill in title, author, max volumes, and pick statuses, then *Create Collection*.
3. **Add a book**: click *Crear Libro*, enter the volume number, publisher, language, condition, and select which collection it belongs to. The collection's *owned* counter increases automatically.
4. **Edit a book**: *Editar Libro* — enter the book's DB id; the fields are unlocked and pre-filled, then hit *Save*.
5. **Edit a collection**: *Editar Colección* — pick the collection from the dropdown to load its data, modify, and *Save*.
6. **Delete**: *Eliminar Libro* / *Eliminar Colección* — pick an entry from the dropdown and confirm.
7. **View a collection's details**: double-click a row in the overview table.

Status values used by the app:

| Field | Possible values |
|-------|-----------------|
| Collection status | `stopped`, `finished`, `onreading` |
| Publication status | `cancelled`, `finished`, `hiatus`, `ongoing` |
| Book (volume) status | `stopped`, `finished`, `onreading` |

