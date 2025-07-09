INSERT INTO users (id, firstname, lastname, birth_date, number_inn, snils, passport_number, login, password) VALUES
('8e9774e8-1546-4d9d-ae97-e9c3978ec7e1','Иван', 'Петров', '1990-05-15', '123456789012', '12345678901', '4512123456', 'ivan_petrov', '$2a$12$syShe3XoUwIxuhs7tnphk.GzHRIEGt/0OwbgkRFHnuElFp2EYYVMi'), -- password1
('8e9774e8-1546-4d9d-ae97-e9c3978ec7e2','Мария', 'Сидорова', '1985-11-22', '234567890123', '23456789012', '4513234567', 'maria_sidorova', '$2a$12$TKIbS73BzYB1n7f1v9Esj.4bEdGlRNyQpuAAZIicT305F1G9D1h3u'), -- password2
('8e9774e8-1546-4d9d-ae97-e9c3978ec7e3','Алексей', 'Иванов', '1995-03-10', '345678901234', '34567890123', '4514345678', 'alexey_ivanov', '$2a$12$rHNquz8BC9onvpe73ByXc.9tM.r7eCCDFXAFmB.gkwUHLbvdwnO7W'), -- password3
('8e9774e8-1546-4d9d-ae97-e9c3978ec7e4','Елена', 'Смирнова', '1988-07-30', '456789012345', '45678901234', '4515456789', 'elena_smirnova', '$2a$12$9VFVsoFbYNS63cUkcnaSpeLNZPxyVJEpWC6oI5JGUZ7FuA9VTAU2G'), -- password4
('8e9774e8-1546-4d9d-ae97-e9c3978ec7e5','Дмитрий', 'Козлов', '1992-12-05', '567890123456', '56789012345', '4516567890', 'dmitry_kozlov', '$2a$12$85LCoNY22ULYcyhyk4yUeuyPpa8B4qMSdKOCV4akutGJGws9Zylq2'); -- password5

INSERT INTO user_roles (user_id, role)
VALUES ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e1', 'ADMIN'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e1', 'USER'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e2', 'USER'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e3', 'USER'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e4', 'ADMIN'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e4', 'USER'),
       ('8e9774e8-1546-4d9d-ae97-e9c3978ec7e5', 'USER');