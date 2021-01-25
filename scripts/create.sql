CREATE TABLE city (
 id_city serial PRIMARY KEY,
 name varchar(60),
 state varchar(2) 
);
commit;

CREATE TABLE person (
 id_person serial PRIMARY KEY,
 name varchar(60),
 gender int,
 date_birth date,
 age int,
 id_city int,
 FOREIGN KEY (id_city) REFERENCES city (id_city)
 
);

commit;