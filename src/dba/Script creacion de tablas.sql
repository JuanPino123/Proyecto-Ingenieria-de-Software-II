DROP TABLE IF EXISTS evaluations;
DROP TABLE IF EXISTS thesis;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS faculty;


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
	id integer PRIMARY KEY,
	name varchar(100) NOT NULL,
	lastName varchar(100) NOT NULL,
	phone varchar(15),
	email varchar(50)
);

CREATE TABLE student(
	id integer PRIMARY KEY,
	cod varchar(100),
	idProgram integer NOT NULL,
	idPerson integer NOT NULL,
	CONSTRAINT fk_student_program foreign KEY (idProgram) REFERENCES program(id),
	CONSTRAINT fk_person_program foreign KEY (idPerson) REFERENCES PERSON(id)
);

CREATE TABLE teacher(
	id integer PRIMARY KEY,
	idPerson integer NOT NULL,
	CONSTRAINT fk_teacher_program foreign KEY (idPerson) REFERENCES PERSON(id)
);

CREATE TABLE thesis(
	id integer PRIMARY KEY,
	title varchar(100),
	status varchar(50),
	idStudent integer NOT NULL,
	idTeacher integer NOT NULL,
	CONSTRAINT fk_thesis_student foreign KEY (idStudent) REFERENCES student(id),
	CONSTRAINT fk_thesis_teacher foreign KEY (idTeacher) REFERENCES teacher(id)
);

CREATE TABLE evaluations(
	id integer PRIMARY KEY,
	qualification varchar(10) NOT NULL,
	observations varchar(5000),
	createAt date,
	idThesis integer NOT NULL,
	idTeacher integer NOT NULL,
	CONSTRAINT fk_evaluations_thesis foreign KEY (idThesis) REFERENCES thesis(id),
	CONSTRAINT fk_evaluations_teacher foreign KEY (idTeacher) REFERENCES teacher(id)
);