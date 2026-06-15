-- Insertar en Usuarios
INSERT INTO USUARIO (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'fran', 'fran@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO PACIENTE (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, dni, direccion, notas) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'Fran', 'Ruiz', '2003-02-07', '987654321', '98786634Q', 'C/ Avenida Luis Montoto Nº21', 'Este paciente está en tratamiento');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (0,'efcf63cb-addf-4afa-a942-dae91283cd8d');

INSERT INTO USUARIO (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'paciente', 'paciente1@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO PACIENTE (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, dni, direccion, notas) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'Paciente', '1', '2023-07-15', '1232456789', '98786634K', 'C/ Avenida Luis Montoto Nº21', 'Este paciente está en revision');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (2,'f5288a99-f910-4424-961d-d088a01f5ce0');

INSERT INTO USUARIO (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'medico01', 'medico@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO MEDICO (id, nombre, apellidos, especialidad, telefono) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'Medico', '1', 'Pediatría', '654987321');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (1,'1cef9086-93a1-49de-b5e7-fb3d01d44baa');

INSERT INTO USUARIO (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'medico02', 'medico2@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO MEDICO (id, nombre, apellidos, especialidad, telefono) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'Medico', '2', 'Alergologo', '654987321');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (1,'1cef9086-93a1-49de-b5e7-fb3d01d44bab');

