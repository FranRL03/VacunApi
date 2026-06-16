-- Insertar en USERs
INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, role) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'fran', 'fran@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'PATIENT');
INSERT INTO PATIENT (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, dni, direccion, notas) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'Fran', 'Ruiz', '2003-02-07', '987654321', '98786634Q', 'C/ Avenida Luis Montoto Nº21', 'Este PATIENT está en tratamiento');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, role) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'PATIENT', 'PATIENT1@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'PATIENT');
INSERT INTO PATIENT (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, dni, direccion, notas) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'PATIENT', '1', '2023-07-15', '1232456789', '98786634K', 'C/ Avenida Luis Montoto Nº21', 'Este PATIENT está en revision');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, role) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR01', 'DOCTOR@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'DOCTOR');
INSERT INTO DOCTOR (id, nombre, apellidos, especialidad, telefono) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR', '1', 'Pediatría', '654987321');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, role) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR02', 'DOCTOR2@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'DOCTOR');
INSERT INTO DOCTOR (id, nombre, apellidos, especialidad, telefono) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR', '2', 'Alergologo', '654987321');

