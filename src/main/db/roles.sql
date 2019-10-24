CREATE TYPE role AS ENUM ('user', 'admin');
CREATE TABLE roles (
    id SERIAL NOT NULL PRIMARY KEY,
    role role
);
INSERT INTO roles (id, role) VALUES (1,'user');
INSERT INTO roles (id, role) VALUES (2,'admin');