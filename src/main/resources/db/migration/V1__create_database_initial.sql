-- Creacion de tabla de roles
CREATE TABLE db_customers.roles
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creacion de tabla de usuarios
CREATE TABLE db_customers.users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY fk_db_customer_roles (role_id) REFERENCES roles (id)
);

-- Creacion de la tabla de customers
CREATE TABLE db_customers.customers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    lastname   VARCHAR(255) NOT NULL,
    age        INT          NOT NULL,
    date_birth  DATE         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);