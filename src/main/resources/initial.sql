-- Insertar datos en la tabla usuario para PROFESOR
INSERT INTO usuario (rol, apellidos, blocked, email, nombre, password, aula)
VALUES
    ('ADMINISTRADOR', 'admin2', FALSE, 'admin@g.educaand.es', 'admin2', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Pérez García', FALSE, 'profesor1@g.educaand.es', 'Juan', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'López Fernández', FALSE, 'profesor2@g.educaand.es', 'Pedro', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Martínez Sánchez', FALSE, 'profesor3@g.educaand.es', 'Ana', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK',NULL),
    ('PROFESOR', 'Gómez Rodríguez', FALSE, 'profesor4@g.educaand.es', 'María', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Sánchez Martínez', FALSE, 'profesor5@g.educaand.es', 'David', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'García Hernández', FALSE, 'profesor6@g.educaand.es', 'Laura', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Hernández López', FALSE, 'profesor7@g.educaand.es', 'José', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Díaz Gómez', FALSE, 'profesor8@g.educaand.es', 'Marta', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Fernández Martínez', FALSE, 'profesor9@g.educaand.es', 'Pablo', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'López Sánchez', FALSE, 'profesor10@g.educaand.es', 'Isabel', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Gómez Pérez', FALSE, 'profesor11@g.educaand.es', 'Francisco', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Pérez Martínez', FALSE, 'profesor12@g.educaand.es', 'Carmen', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Martínez González', FALSE, 'profesor13@g.educaand.es', 'Javier', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'González Díaz', FALSE, 'profesor14@g.educaand.es', 'Sofía', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('PROFESOR', 'Sánchez Fernández', FALSE, 'profesor15@g.educaand.es', 'Carlos', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', NULL),
    ('ESTUDIANTE', 'García Pérez', FALSE, 'estudiante1@g.educaand.es', 'Laura', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Fernández López', FALSE, 'estudiante2@g.educaand.es', 'Carlos', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Sánchez Martínez', FALSE, 'estudiante3@g.educaand.es', 'Sofía', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Rodríguez Gómez', FALSE, 'estudiante4@g.educaand.es', 'Pablo', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 101'),
    ('ESTUDIANTE', 'Martínez Sánchez', FALSE, 'estudiante5@g.educaand.es', 'Lucía', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'López Fernández', FALSE, 'estudiante6@g.educaand.es', 'Elena', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'García Pérez', FALSE, 'estudiante7@g.educaand.es', 'Daniel', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Fernández López', FALSE, 'estudiante8@g.educaand.es', 'Andrea', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Sánchez Martínez', FALSE, 'estudiante9@g.educaand.es', 'Adrián', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Rodríguez Gómez', FALSE, 'estudiante10@g.educaand.es', 'Julia', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 102'),
    ('ESTUDIANTE', 'Martínez Sánchez', FALSE, 'estudiante11@g.educaand.es', 'Alejandro', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'López Fernández', FALSE, 'estudiante12@g.educaand.es', 'Paula', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'García Pérez', FALSE, 'estudiante13@g.educaand.es', 'Miguel', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Fernández López', FALSE, 'estudiante14@g.educaand.es', 'Carmen', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Sánchez Martínez', FALSE, 'estudiante15@g.educaand.es', 'Hugo', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Rodríguez Gómez', FALSE, 'estudiante16@g.educaand.es', 'Natalia', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 103'),
    ('ESTUDIANTE', 'Martínez Sánchez', FALSE, 'estudiante17@g.educaand.es', 'Diego', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'López Fernández', FALSE, 'estudiante18@g.educaand.es', 'Eva', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'García Pérez', FALSE, 'estudiante19@g.educaand.es', 'Javier', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104'),
    ('ESTUDIANTE', 'Fernández López', FALSE, 'estudiante20@g.educaand.es', 'Sara', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK', 'Aula 104');

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

INSERT INTO aula (anio, grupo, clase_id, propietario_id, eliminado) VALUES ('23/24', 'A', 1, 2, false);
INSERT INTO aula (anio, grupo, clase_id, propietario_id, eliminado) VALUES ('23/24', 'B', 2, 2, false);
INSERT INTO aula (anio, grupo, clase_id, propietario_id, eliminado) VALUES ('23/24', 'C', 3, 3, false);
INSERT INTO aula (anio, grupo, clase_id, propietario_id, eliminado) VALUES ('23/24', 'D', 4, 4, false);
INSERT INTO aula (anio, grupo, clase_id, propietario_id, eliminado) VALUES ('23/24', 'E', 5, 5, false);


INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (1, 2);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (1, 3);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (2, 4);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (2, 5);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (3, 6);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (1, 4);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (2, 2);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (3, 3);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (4, 4);
INSERT INTO aula_profesores (aulas_id, profesores_id) VALUES (5, 5);

INSERT INTO aula_estudiantes (aulas_id, estudiantes_id) VALUES (1, 20);
INSERT INTO aula_estudiantes (aulas_id, estudiantes_id) VALUES (1, 21);
INSERT INTO aula_estudiantes (aulas_id, estudiantes_id) VALUES (1, 22);
INSERT INTO aula_estudiantes (aulas_id, estudiantes_id) VALUES (1, 23);
INSERT INTO aula_estudiantes (aulas_id, estudiantes_id) VALUES (1, 24);

INSERT INTO tema (nombre, aula_id, eliminado) VALUES ('Tema 1', 1, false);
INSERT INTO tema (nombre, aula_id, eliminado) VALUES ('Tema 2', 2, false);
INSERT INTO tema (nombre, aula_id, eliminado) VALUES ('Tema 3', 3, false);
INSERT INTO tema (nombre, aula_id, eliminado) VALUES ('Tema 4', 4, false);
INSERT INTO tema (nombre, aula_id, eliminado) VALUES ('Tema 5', 5, false);

-- Insertar datos en la tabla tarea con propietario como uno de los profesores del aula que contiene el tema
INSERT INTO tarea (nombre, propietario_id, tema_id, eliminado, visible)
VALUES ('Tarea 1', 2, 1, false, true);

INSERT INTO tarea (nombre, propietario_id, tema_id, eliminado, visible)
VALUES ('Tarea 2', 3, 2, false, true);

INSERT INTO tarea (nombre, propietario_id, tema_id, eliminado, visible)
VALUES ('Tarea 3', 3, 3, false, true);

-- Insertar datos en la tabla fase
INSERT INTO fase (tarea_id,nombre_archivo,nivel) VALUES (1,'video',1);
INSERT INTO fase (tarea_id,nombre_archivo,nivel) VALUES (1,'foto',2);
INSERT INTO fase (tarea_id,nombre_archivo,nivel) VALUES (1,'audio',3);

-- Insertar datos en la tabla tarea_estudiante
INSERT INTO tarea_estudiante (fase, estudiante_id, tarea_id)
VALUES (1, 20, 2);
INSERT INTO tarea_estudiante (fase, basico, intermedio, estudiante_id, tarea_id)
VALUES (2, 85, 80, 21, 1);
INSERT INTO tarea_estudiante (fase, basico, intermedio, avanzado, estudiante_id, tarea_id)
VALUES (3, 90, 85, 80, 22, 3);
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
