-- Creacion de tabla de roles
CREATE TABLE db_customers.roles
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) UNIQUE NOT NULL COMMENT 'Nombre del role',
    description VARCHAR(255) COMMENT 'Descripcion del role',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) COMMENT = 'Tabla que almacena información de los roles';

-- Creacion de tabla de usuarios
CREATE TABLE db_customers.users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL COMMENT 'Email del usuario',
    password   VARCHAR(255)        NOT NULL COMMENT 'Contraseña encriptada',
    role_id    INT COMMENT 'Identificador del rol del usuario',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY fk_db_customer_roles (role_id) REFERENCES roles (id)
) COMMENT = 'Tabla que almacena información de los usuarios';

-- Creacion de la tabla de customers
CREATE TABLE db_customers.customers
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255) NOT NULL COMMENT 'Nombres',
    lastname         VARCHAR(255) NOT NULL COMMENT 'Apellidos',
    age              INT          NOT NULL COMMENT 'Edad',
    date_birth       DATE         NOT NULL COMMENT 'Fecha de nacimiento',
    date_death       DATE         NULL COMMENT 'Fecha estimada de muerte o fallecimiento',
    date_with_drawal DATE         NULL COMMENT 'Fecha estimada de retiro o de jubilacion',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) COMMENT = 'Tabla que almacena información de los empleados';

-- Creando indices
-- Indexado el email de los usuarios
CREATE INDEX idx_user_email on db_customers.users (email);

-- Indexado del customer
CREATE INDEX idx_customer_name on db_customers.customers (name);
CREATE INDEX idx_customer_lastname on db_customers.customers (lastname);
CREATE INDEX idx_customer_lastname_name on db_customers.customers (name, lastname);