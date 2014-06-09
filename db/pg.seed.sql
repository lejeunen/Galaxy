
-- key sequence for primary keys allocation

CREATE SEQUENCE key_sequence INCREMENT BY 1 START WITH 1000;

-- galaxy data

CREATE TABLE tGalaxy (
	primaryKey BIGINT PRIMARY KEY,
	name VARCHAR(80) CONSTRAINT unique_galaxy_name UNIQUE,
	alternateName VARCHAR(80),
	distanceFromEarth VARCHAR(80),
	nbStars VARCHAR(80)
);

INSERT INTO tGalaxy (primaryKey, name, alternateName, distanceFromEarth, nbStars)
VALUES (1, 'Andromeda Galaxy', ' Messier 31', '2.5 million light-years', 'one trillion');

INSERT INTO tGalaxy (primaryKey, name, alternateName, distanceFromEarth, nbStars)
VALUES (2, 'Magellanic Clouds', 'Nubeculae Magellan', '160,000 light-years', '');

INSERT INTO tGalaxy (primaryKey, name, alternateName, distanceFromEarth, nbStars)
VALUES (3, 'Sombrero Galaxy', 'Messier Object 104', '28 million light-years', '');

INSERT INTO tGalaxy (primaryKey, name, alternateName, distanceFromEarth, nbStars)
VALUES (4, 'Whirlpool Galaxy', 'Messier 51a', '23 million light-years', '');

-- planet data

CREATE TABLE tPlanet (
	primaryKey BIGINT PRIMARY KEY,
	name VARCHAR(80) CONSTRAINT unique_planet_name UNIQUE,
	orbitalSpeed VARCHAR(80),
	distanceFromSun VARCHAR(80),
	radius VARCHAR(80)
);

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (1, 'Mercury', '47.362 km/s', 'from 46,000,000 km to 70,000,000 km', '2439.7 km');

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (2, 'Venus', '35.02 km/s', '108,000,000 km', '6051.8 km');

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (3, 'Earth', '29.78 km/s', '150,000,000 km', '6371.0 km');

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (4, 'Mars', '24.077 km/s', '230,000,000 km', '3389.5 km');

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (5, 'Vesta', '19.34 km/s', '', '');

INSERT INTO tPlanet (primaryKey, name, orbitalSpeed, distanceFromSun, radius)
VALUES (6, 'Ceres', '17.882 km/s', '419,000,000 km', '476.2 km');

-- company data

CREATE TABLE tCompany (
	primaryKey BIGINT PRIMARY KEY,
	name VARCHAR(80) CONSTRAINT unique_company_name UNIQUE,
	edition VARCHAR(80),
	notice VARCHAR(80)
);

INSERT INTO tCompany (primaryKey, name, edition, notice)
VALUES (1, 'Acme', 'Free', '');

INSERT INTO tCompany (primaryKey, name, edition, notice)
VALUES (2, 'Nova', 'Free', '');

INSERT INTO tCompany (primaryKey, name, edition, notice)
VALUES (3, 'Gama', 'Free', '');

-- user data

CREATE TABLE tUser (
	primaryKey BIGINT PRIMARY KEY,
	firstName VARCHAR(80),
	lastName VARCHAR(80),
	userName VARCHAR(80) CONSTRAINT unique_user_username UNIQUE,
	email VARCHAR(255) CONSTRAINT unique_user_email UNIQUE,
	openId VARCHAR(255),
	company BIGINT REFERENCES tCompany ON DELETE CASCADE,
	password VARCHAR(80),
	isAdmin BOOLEAN
);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (0, 'Admin', 'Admin', 'admin', 'admin@email.com', '', 1, 'admin', true);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (1, 'Yan', 'Avery', 'yavery', 'yavery@email.com', '', 1, 'On3On3', true);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (2, 'Karine', 'Fiset', 'kfiset', 'kfiset@email.com', '', 1, 'Tw0Tw0', false);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (3, 'Frank', 'Morgan', 'fmorgan', 'fmorgan@email.com', '', 1, 'Tw0Tw0', false);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (4, 'Adam', 'Smith', 'asmith', 'asmith@email.com', '', 2, 'Tw0Tw0', false);

INSERT INTO tUser (primaryKey, firstName, lastName, userName, email, openId, company, password, isAdmin)
VALUES (5, 'Peter', 'Morris', 'pmorris', 'pmorris@email.com', '', 3, 'Tw0Tw0', false);
