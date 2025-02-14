-- Creacion de tabla de roles
CREATE TABLE DB_SEEK_CUSTOMERS.ROLES
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creacion de tabla de usuarios
CREATE TABLE DB_SEEK_CUSTOMERS.USERS
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY Fk_DB_SEEK_CUSTOMERS_USERS_ROLES (role_id) REFERENCES ROLES (id)
);

-- Creacion de la tabla de customers
CREATE TABLE DB_SEEK_CUSTOMERS.CUSTOMERS
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    lastname   VARCHAR(255) NOT NULL,
    age        INT          NOT NULL,
    date_bith  DATE         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);