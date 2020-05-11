drop table if exists user cascade;
drop table if exists karten cascade;
drop table if exists popups cascade;
drop table if exists projekte cascade;



create table user(
	userid		SERIAL PRIMARY KEY,
	vorname		VARCHAR(100),
	nachname	VARCHAR(100),
	Straße 		VARCHAR(200),
	Hausnummer	INTEGER,
	PLZ 		LONG,
	Ort		VARCHAR(100)
	
);

create table karten(
	KartenId SERIAL PRIMARY KEY,
	name	VARCHAR(100),
	Karten geometry(MULTIPOLYGON,4326),
	PopupId INTEGER REFERENCES popups(popupid),
);

create table popups(
	PopupId SERIAL	PRIMARY KEY,
	Info 	VARCHAR(600)
);

create table gegenstaende(
	gegenstandsId	SERIAL PRIMARY KEY,
	gegenstand	URL
);

create table projekte(
	projektnr	SERIAL PRIMARY KEY,
	projekte 	URL,
	status		VARCHAR(100),
	erstellt_von	REFERENCES user(userid)
);

