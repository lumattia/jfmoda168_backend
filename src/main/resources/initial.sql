-- Insertar datos en la tabla usuario para PROFESOR
INSERT INTO usuario (rol, apellido1, apellido2, blocked, email, nombre, password, aula)
VALUES
    ('ADMINISTRADOR', 'admin2', 'admin2', FALSE, 'admin2@g.educaand.es', 'admin2', '$2a$10$sChCnm2rU64I3VgoChL4Ou7YZenIWFcgAOZObEMfHkOmDoyq9prhK', NULL),
    ('PROFESOR', 'Pérez', 'García', FALSE, 'profesor1@example.com', 'Juan', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'López', 'Fernández', FALSE, 'profesor2@example.com', 'Pedro', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Martínez', 'Sánchez', FALSE, 'profesor3@example.com', 'Ana', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK',NULL),
    ('PROFESOR', 'Gómez', 'Rodríguez', FALSE, 'profesor4@example.com', 'María', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Sánchez', 'Martínez', FALSE, 'profesor5@example.com', 'David', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('ESTUDIANTE', 'García', 'Pérez', FALSE, 'estudiante1@example.com', 'Laura', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Fernández', 'López', FALSE, 'estudiante2@example.com', 'Carlos', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Sánchez', 'Martínez', FALSE, 'estudiante3@example.com', 'Sofía', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Rodríguez', 'Gómez', FALSE, 'estudiante4@example.com', 'Pablo', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Martínez', 'Sánchez', FALSE, 'estudiante5@example.com', 'Lucía', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'López', 'Fernández', FALSE, 'estudiante6@example.com', 'Elena', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'García', 'Pérez', FALSE, 'estudiante7@example.com', 'Daniel', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Fernández', 'López', FALSE, 'estudiante8@example.com', 'Andrea', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Sánchez', 'Martínez', FALSE, 'estudiante9@example.com', 'Adrián', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Rodríguez', 'Gómez', FALSE, 'estudiante10@example.com', 'Julia', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Martínez', 'Sánchez', FALSE, 'estudiante11@example.com', 'Alejandro', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'López', 'Fernández', FALSE, 'estudiante12@example.com', 'Paula', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'García', 'Pérez', FALSE, 'estudiante13@example.com', 'Miguel', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Fernández', 'López', FALSE, 'estudiante14@example.com', 'Carmen', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Sánchez', 'Martínez', FALSE, 'estudiante15@example.com', 'Hugo', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Rodríguez', 'Gómez', FALSE, 'estudiante16@example.com', 'Natalia', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Martínez', 'Sánchez', FALSE, 'estudiante17@example.com', 'Diego', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'López', 'Fernández', FALSE, 'estudiante18@example.com', 'Eva', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'García', 'Pérez', FALSE, 'estudiante19@example.com', 'Javier', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'Fernández', 'López', FALSE, 'estudiante20@example.com', 'Sara', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104');

INSERT INTO asignatura (nombre) VALUES ('Matemáticas');
INSERT INTO asignatura (nombre) VALUES ('Lengua y Literatura');
INSERT INTO asignatura (nombre) VALUES ('Ciencias Naturales');
INSERT INTO asignatura (nombre) VALUES ('Historia');
INSERT INTO asignatura (nombre) VALUES ('Educación Física');

INSERT INTO curso (nombre) VALUES ('1º ESO');
INSERT INTO curso (nombre) VALUES ('2º ESO');
INSERT INTO curso (nombre) VALUES ('3º ESO');
INSERT INTO curso (nombre) VALUES ('4º ESO');
INSERT INTO curso (nombre) VALUES ('1º Bachillerato');
INSERT INTO curso (nombre) VALUES ('2º Bachillerato');

INSERT INTO clase (asignatura_id, curso_id) VALUES (1, 1);
INSERT INTO clase (asignatura_id, curso_id) VALUES (2, 1);
INSERT INTO clase (asignatura_id, curso_id) VALUES (3, 2);
INSERT INTO clase (asignatura_id, curso_id) VALUES (4, 2);
INSERT INTO clase (asignatura_id, curso_id) VALUES (1, 3);

INSERT INTO clase_profesores (clases_id, profesores_id) VALUES (1, 2);
INSERT INTO clase_profesores (clases_id, profesores_id) VALUES (2, 3);
INSERT INTO clase_profesores (clases_id, profesores_id) VALUES (3, 4);
INSERT INTO clase_profesores (clases_id, profesores_id) VALUES (4, 5);
INSERT INTO clase_profesores (clases_id, profesores_id) VALUES (5, 6);

INSERT INTO aula (años, eliminado, grupo, clase_id, propietario_id) VALUES ('23/24', 0, 'A', 1, 1);
INSERT INTO aula (años, eliminado, grupo, clase_id, propietario_id) VALUES ('23/24', 0, 'B', 2, 2);
INSERT INTO aula (años, eliminado, grupo, clase_id, propietario_id) VALUES ('23/24', 0, 'C', 3, 3);
INSERT INTO aula (años, eliminado, grupo, clase_id, propietario_id) VALUES ('23/24', 0, 'D', 4, 4);
INSERT INTO aula (años, eliminado, grupo, clase_id, propietario_id) VALUES ('23/24', 0, 'E', 5, 5);

INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (1, 2);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (1, 3);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (2, 4);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (2, 5);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (3, 6);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (1, 1);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (2, 2);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (3, 3);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (4, 4);
INSERT INTO aula_profesores (aula_id, profesores_id) VALUES (5, 5);

INSERT INTO aula_estudiantes (aula_id, estudiantes_id) VALUES (1, 6);
INSERT INTO aula_estudiantes (aula_id, estudiantes_id) VALUES (1, 7);
INSERT INTO aula_estudiantes (aula_id, estudiantes_id) VALUES (1, 8);
INSERT INTO aula_estudiantes (aula_id, estudiantes_id) VALUES (1, 9);
INSERT INTO aula_estudiantes (aula_id, estudiantes_id) VALUES (1, 10);

INSERT INTO tema (clase_id, nombre, aula_id) VALUES (1, 'Tema 1', 1);
INSERT INTO tema (clase_id, nombre, aula_id) VALUES (2, 'Tema 2', 2);
INSERT INTO tema (clase_id, nombre, aula_id) VALUES (3, 'Tema 3', 3);
INSERT INTO tema (clase_id, nombre, aula_id) VALUES (4, 'Tema 4', 4);
INSERT INTO tema (clase_id, nombre, aula_id) VALUES (5, 'Tema 5', 5);
-- Insertar datos en la tabla fase
INSERT INTO fase (nombre_archivo) VALUES ('video');
INSERT INTO fase (nombre_archivo) VALUES ('foto');
INSERT INTO fase (nombre_archivo) VALUES ('audio');

-- Insertar datos en la tabla tarea con propietario como uno de los profesores del aula que contiene el tema
INSERT INTO tarea (nombre, avanzado_id, basico_id, intermedio_id, propietario_id, tema_id)
VALUES ('Tarea 1', 1, 2, 3, 2, 1);
INSERT INTO tarea (nombre, avanzado_id, basico_id, intermedio_id, propietario_id, tema_id)
VALUES ('Tarea 2', 2, 3, 1, 3, 2);
INSERT INTO tarea (nombre, avanzado_id, basico_id, intermedio_id, propietario_id, tema_id)
VALUES ('Tarea 3', 3, 1, 2, 3, 3);
-- Insertar datos en la tabla tarea_estudiante
INSERT INTO tarea_estudiante (fase, estudiante_id, tarea_id)
VALUES ('basica', 1, 2);
INSERT INTO tarea_estudiante (fase, puntuacion_basica, puntuacion_intermedia, estudiante_id, tarea_id)
VALUES ('intermedia', 85, 80, 3, 4);
INSERT INTO tarea_estudiante (fase, puntuacion_basica, puntuacion_intermedia, puntuacion_avanzada, estudiante_id, tarea_id)
VALUES ('avanzada', 90, 85, 80, 4, 5);
INSERT INTO pregunta (enunciado, nombre_archivo, fase_id) VALUES ('Enunciado pregunta 1', 'archivo1.jpg', 1);
INSERT INTO pregunta (enunciado, nombre_archivo, fase_id) VALUES ('Enunciado pregunta 2', 'archivo2.mp3', 2);
INSERT INTO pregunta (enunciado, nombre_archivo, fase_id) VALUES ('Enunciado pregunta 3', 'archivo3.mp4', 3);
-- Insertar datos en la tabla respuesta
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (1, 'Respuesta correcta 1', 1);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 1', 1);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 2', 1);

INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 1', 2);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (1, 'Respuesta correcta 2', 2);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 3', 2);

INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 1', 3);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (0, 'Respuesta incorrecta 2', 3);
INSERT INTO respuesta (correcta, respuesta, pregunta_id) VALUES (1, 'Respuesta correcta 3', 3);
