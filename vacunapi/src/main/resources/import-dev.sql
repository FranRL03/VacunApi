-- INSERT USERS

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled, rol) VALUES ('f5288a99-f910-4424-961d-d088a01f5ce1', 'admin', 'admin@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true, 'ADMIN');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR01', 'doctor@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO DOCTOR (id, name, last_name, speciality, phone) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44baa', 'DOCTOR', '1', 'Pediatría', '654987321');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR02', 'doctor2@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO DOCTOR (id, name, last_name, speciality, phone) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bab', 'DOCTOR', '2', 'Alergologo', '654987321');

INSERT INTO USERS (id, username, email, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bac', 'PATIENT01', 'patient@example.com', '{bcrypt}$2a$12$qG8MR/W6ExYtPPdpr2Ry/eEy.x30mvHWlRyLuG6fP57lCK.6Kuz.W', true, true, true, true);
INSERT INTO PATIENT (id, name, last_name, phone, birthday, dni, address) VALUES ('1cef9086-93a1-49de-b5e7-fb3d01d44bac', 'PATIENT', '1', '654987321', '2000-03-27', '23744828K', 'C/ Alvarez Quintero Nº 21');

-- INSERTS AGENDAS

INSERT INTO DOCTOR_AGENDA (id, day_of_week, start_time, end_time, duration, active, doctor_id) VALUES ('2cef9086-93a1-49de-b5e7-fb3d01d44baa', 3, '09:00', '14:00', 20, true, '1cef9086-93a1-49de-b5e7-fb3d01d44baa');
INSERT INTO DOCTOR_AGENDA (id, day_of_week, start_time, end_time, duration, active, doctor_id) VALUES ('2cef9086-93a1-49de-b5e7-fb3d01d44bab', 2, '09:00', '15:00', 20, false, '1cef9086-93a1-49de-b5e7-fb3d01d44baa');
INSERT INTO DOCTOR_AGENDA (id, day_of_week, start_time, end_time, duration, active, doctor_id) VALUES ('2cef9086-93a1-49de-b5e7-fb3d01d44bac', 1, '08:30', '14:00', 20, true, '1cef9086-93a1-49de-b5e7-fb3d01d44baa');

-- INSERTS APPOINTMENT

INSERT INTO APPOINTMENT (id, start_date_time, end_date_time, reason, notes, room, state, patient_id, doctor_id) VALUES ('3cef9086-93a1-49de-b5e7-fb3d01d44baa', '2026-06-26 10:00', '2026-06-26 10:20', 'revision', '', 'Room 1-A', 'PENDING', '1cef9086-93a1-49de-b5e7-fb3d01d44bac', '1cef9086-93a1-49de-b5e7-fb3d01d44baa')