DROP TABLE IF EXISTS hero_power, superheroes, assistants, powers;

CREATE TABLE superheroes (
	hero_id serial PRIMARY KEY,
	hero_name varchar(50),
	hero_alias varchar(50),
	hero_origin varchar(50)
); 

CREATE TABLE assistants (
	assist_id serial PRIMARY KEY,
	assist_name varchar(50),
	hero_id int
);

CREATE TABLE powers (
	power_id serial PRIMARY KEY,
	power_name varchar(50),
	power_description varchar(200)
);