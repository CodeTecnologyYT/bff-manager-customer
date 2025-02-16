-- Creacion de data de prueba roles
INSERT INTO db_customers.roles (name, description)
VALUES ('ADMIN', 'Administrator role with full permissions');

INSERT INTO db_customers.roles (name, description)
VALUES ('USER', 'Standard user with limited permissions');

-- Creacion de data de prueba usuario
-- Contraseña 1234
INSERT INTO db_customers.users (email, password, role_id)
VALUES ('admin@example.com', '$2a$10$BV4RSbTmtNAzCTPh1BOXDuQ1UdrV5piRpCjlNXGQzWatbyY1MCOU6', 1);

-- Contraseña 1234
INSERT INTO db_customers.users (email, password, role_id)
VALUES ('user@example.com', '$2a$10$BV4RSbTmtNAzCTPh1BOXDuQ1UdrV5piRpCjlNXGQzWatbyY1MCOU6', 2);

-- Creacion de data de prueba empleados
INSERT INTO db_customers.customers (name, lastname, age, date_birth)
VALUES ('Juan', 'Pérez', 30, '1994-05-15');

INSERT INTO db_customers.customers (name, lastname, age, date_birth)
VALUES ('María', 'González', 25, '1999-08-22');

COMMIT;