CREATE TABLE users (
    id  SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR UNIQUE NOT NULL,
    active BOOLEAN,
    role_id integer,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO users (username,password,active,role_id) VALUES ('admin','admin',true,2);
INSERT INTO users (username,password,active,role_id) VALUES ('user','user',true,1);