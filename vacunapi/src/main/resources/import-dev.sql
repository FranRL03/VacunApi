-- Insertar en USERs
INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, rol) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce1', 'admin', 'admin@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'ADMIN');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR01', 'doctor@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO DOCTOR (id, name, last_name, speciality, phone) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR', '1', 'Pediatría', '654987321');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR02', 'doctor2@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO DOCTOR (id, name, last_name, speciality, phone) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR', '2', 'Alergologo', '654987321');

