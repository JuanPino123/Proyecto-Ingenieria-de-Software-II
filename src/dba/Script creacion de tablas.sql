DROP TABLE IF EXISTS evaluations;
DROP TABLE IF EXISTS thesis;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS roles;
drop table if exists users;


CREATE TABLE faculty(
	id integer PRIMARY KEY,
	name varchar(100) NOT null
);


CREATE TABLE program(
	id integer PRIMARY KEY,
	name varchar(100) NOT NULL,
	idFaculty integer NOT NULL,
	CONSTRAINT fk_faculty_program foreign KEY (idFaculty) REFERENCES faculty(id)
);


CREATE TABLE person(
	id integer PRIMARY KEY autoincrement,
	name varchar(100) NOT NULL,
	lastName varchar(100) NOT NULL,
	phone varchar(15)
);

CREATE TABLE student(
	id integer PRIMARY KEY autoincrement,
	cod varchar(100),
	idProgram integer NOT NULL,
	idPerson integer NOT NULL,
	CONSTRAINT fk_student_program foreign KEY (idProgram) REFERENCES program(id),
	CONSTRAINT fk_person_program foreign KEY (idPerson) REFERENCES PERSON(id)
);

CREATE TABLE teacher(
	id integer PRIMARY KEY autoincrement,
	idPerson integer NOT NULL,
	CONSTRAINT fk_teacher_program foreign KEY (idPerson) REFERENCES PERSON(id)
);

CREATE TABLE thesis(
	id integer PRIMARY KEY autoincrement,
	title varchar(100),
	status varchar(50),
	idStudent integer NOT NULL,
	idTeacher integer NOT NULL,
	CONSTRAINT fk_thesis_student foreign KEY (idStudent) REFERENCES student(id),
	CONSTRAINT fk_thesis_teacher foreign KEY (idTeacher) REFERENCES teacher(id)
);

CREATE TABLE evaluations(
	id integer PRIMARY KEY autoincrement,
	qualification varchar(10) NOT NULL,
	observations varchar(5000),
	createAt date,
	idThesis integer NOT NULL,
	idTeacher integer NOT NULL,
	CONSTRAINT fk_evaluations_thesis foreign KEY (idThesis) REFERENCES thesis(id),
	CONSTRAINT fk_evaluations_teacher foreign KEY (idTeacher) REFERENCES teacher(id)
);


CREATE TABLE roles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    rol VARCHAR(50) NOT NULL,
    CHECK (rol IN ('ESTUDIANTE', 'DIRECTOR', 'COORDINADOR'))
);

CREATE TABLE users(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	email varchar(50) not null,
	password varchar(50) not null,
	idPerson integer not null,
	CONSTRAINT fk_users_person foreign KEY (idPerson) REFERENCES person(id)
);

create table users_roles(
	idUser integer not null,
	idRol integer not null,
	CONSTRAINT pk_user_roles primary key (idUser, idRol),
	CONSTRAINT fk_users_roles_users foreign KEY (idUser) REFERENCES users(id),
	CONSTRAINT fk_users_roles_roles foreign KEY (idRol) REFERENCES roles(id)
);


---Insert
delete from faculty;

insert into faculty values(1,"FACULTAD DE INGENERIA ELECTRONICA Y TELECOMUNICACIONES");

insert into program(name, idFaculty) values ("Ingenieria de Sistemas", 1);
insert into program(name, idFaculty) values ("Ingenieria Electronica y Telecomunicaciones", 1);
insert into program(name, idFaculty) values ("Automatica Industrial", 1);
insert into program(name, idFaculty) values ("Tecnologia en Telematica", 1);

