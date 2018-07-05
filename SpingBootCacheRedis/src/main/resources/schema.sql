DROP TABLE IF EXISTS person;
create table person
(
id NUMERIC(19,0) primary key auto_increment,
name varchar(64),
age integer,
address varchar(64)
);

