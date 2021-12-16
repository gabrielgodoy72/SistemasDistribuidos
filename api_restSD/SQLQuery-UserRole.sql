INSERT INTO roles(descripcion, nombre) VALUES ('ROLE_USER', 'USER');
INSERT INTO roles(descripcion, nombre) VALUES ('ROLE_ADMIN', 'ADMIN');

INSERT INTO usuarios(nombre, apellido, email, username, password) VALUES ('Admin', 'Admin', 'admin@email.com', 'admin', '$2a$12$up6A65LWaQ8Z3w6RnqC88exMzJzuRF48TktpZOBTG549GN2x7omx2');
INSERT INTO usuarios(nombre, apellido, email, username, password) VALUES ('User', 'User', 'user@email.com', 'user', '$2a$12$orI1QJH3/B4vopeLY7ikBOAw8iscKgHzxixURkl3TPpuKjusb2Xty');
INSERT INTO user_role(user_id, role_id) VALUES (5, 2);
INSERT INTO user_role(user_id, role_id) VALUES (6, 1);

SELECT * FROM usuarios
SELECT * FROM roles
SELECT * FROM user_role

