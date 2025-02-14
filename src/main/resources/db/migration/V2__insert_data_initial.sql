-- Creacion de data de prueba roles
INSERT INTO DB_SEEK_CUSTOMERS.ROLES (name, description)
VALUES ('ADMIN', 'Administrator role with full permissions');

INSERT INTO DB_SEEK_CUSTOMERS.ROLES (name, description)
VALUES ('USER', 'Standard user with limited permissions');

-- Creacion de data de prueba usuario
INSERT INTO DB_SEEK_CUSTOMERS.USERS (email, password, role_id)
VALUES ('admin@example.com', 'hashed_password_admin', 1);

INSERT INTO DB_SEEK_CUSTOMERS.USERS (email, password, role_id)
VALUES ('user@example.com', 'hashed_password_user', 2);

-- Creacion de data de prueba empleados
INSERT INTO DB_SEEK_CUSTOMERS.CUSTOMERS (name, lastname, age, date_bith)
VALUES ('Juan', 'Pérez', 30, '1994-05-15');

INSERT INTO DB_SEEK_CUSTOMERS.CUSTOMERS (name, lastname, age, date_bith)
VALUES ('María', 'González', 25, '1999-08-22');

COMMIT;