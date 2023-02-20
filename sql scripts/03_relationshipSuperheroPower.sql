

CREATE TABLE hero_power(
	hero_id int,
	power_id int
);

ALTER TABLE hero_power ADD FOREIGN KEY (power_id) REFERENCES powers(power_id);
ALTER TABLE hero_power ADD FOREIGN KEY (hero_id) REFERENCES superheroes(hero_id);