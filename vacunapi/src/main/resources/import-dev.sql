-- Insertar en Vacuna
INSERT INTO VACUNA (id, nombre, descripcion) VALUES ('e8565422-7485-4633-a187-764050a11db9', 'Alergia', 'Alergia contra el polen y los ácaros');

-- Insertar en Calendario
INSERT INTO CALENDARIO (id, vacuna_id, edad, tipo_dosis, recomendaciones, discriminante) VALUES ('0934fd8a-8ae2-4f78-b287-dffde17bc221', 'e8565422-7485-4633-a187-764050a11db9', 2, 'Primera', 'Reposo durante el día', 'T');
INSERT INTO CALENDARIO (id, vacuna_id, edad, tipo_dosis, recomendaciones, discriminante) VALUES ('85f20290-c4fe-4404-9cd0-14db8707b9e7', 'e8565422-7485-4633-a187-764050a11db9', 6, 'Segunda', 'Ponerse frío', 'H');

-- Insertar en Usuarios
INSERT INTO USUARIO (id, email, password) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'fran@example.com', 'password1');
INSERT INTO PACIENTE (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, notas) VALUES ('efcf63cb-addf-4afa-a942-dae91283cd8d', 'Fran', 'Ruiz', '2003-02-07', '987654321', 'Este paciente está en tratamiento');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (1,'efcf63cb-addf-4afa-a942-dae91283cd8d');

INSERT INTO USUARIO (id, email, password) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'paciente1@example.com', 'password2');
INSERT INTO PACIENTE (id, nombre, apellidos, fecha_nacimiento, telefono_contacto, notas) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce0', 'Paciente', '1', '2023-07-15', '1232456789', 'Este paciente está en revision');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (2,'f5288a99-f910-4424-961d-d088a01f5ce0');

INSERT INTO USUARIO (id, email, password) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'laura.medico@example.com', 'password3');
INSERT INTO MEDICO (id, nombre, apellidos, especialidad, telefono) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'Laura', 'García', 'Pediatría', '654987321');
INSERT INTO USUARIO_ROLES (roles, usuario_id) VALUES (1,'1cef9086-93a1-49de-b5e7-fb3d01d44baa');

-- Insertar en Administracion
INSERT INTO ADMINISTRACION (id, calendario_id, paciente_id, fecha, edad_al_administrar, notas) VALUES ('e6f7fba6-bb90-4645-98d1-36cfb02ae04d', '0934fd8a-8ae2-4f78-b287-dffde17bc221', 'f5288a99-f910-4424-961d-d088a01f5ce0', '2023-10-19', 10, 'EDFWREREV');

--Insertar en Cita
INSERT INTO CITA (id, fecha, motivo, notas, paciente_id, medico_id) VALUES ('d290f1ee-6c54-4b01-90e6-d701748f0851', '2024-06-15 09:30:00', 'Revisión general', 'Revisión anual del paciente', 'f5288a99-f910-4424-961d-d088a01f5ce0', '1cef9086-93a1-49de-b5e7-fb3d01d44baa');
