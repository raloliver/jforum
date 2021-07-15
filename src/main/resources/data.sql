INSERT INTO USER(name, email, password) VALUES('raloliver', 'raloliver@domain.co', '123456');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Backend');
INSERT INTO COURSE(name, category) VALUES('Angular', 'Frontend');

INSERT INTO TOPIC(title, message, date_creation, status, author_id, course_id) VALUES('Dúvida 1', 'Erro Typescript', '2021-07-13 06:22:00', 'UNREPLY', 1, 2);
INSERT INTO TOPIC(title, message, date_creation, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2021-07-14 11:49:52', 'UNREPLY', 1, 1);
INSERT INTO TOPIC(title, message, date_creation, status, author_id, course_id) VALUES('Dúvida 3', 'Java no VSCODE', '2021-07-15 21:49:52', 'UNREPLY', 1, 1);