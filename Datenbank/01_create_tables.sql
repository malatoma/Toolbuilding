drop table if exists users cascade;
drop table if exists karten cascade;
drop table if exists popups cascade;
drop table if exists gegenstaende cascade;
drop table if exists projekte cascade;



create table users(
	userid		SERIAL PRIMARY KEY,
	vorname		VARCHAR(100),
	nachname	VARCHAR(100),
	Straße 		VARCHAR(200),
	Hausnummer	INTEGER,
	PLZ 		VARCHAR(30),
	Ort		VARCHAR(100)
	
);

create table popups(
	PopupId SERIAL	PRIMARY KEY,
	Info 	VARCHAR(600)
);

create table karten(
	KartenId SERIAL PRIMARY KEY,
	name	VARCHAR(100),
	Karten geometry(MULTIPOLYGON,4326),
	PopupId INTEGER REFERENCES popups(PopupId)
);

create table gegenstaende(
	gegenstandsId	SERIAL PRIMARY KEY,
	gegenstand	Text
);

create table projekte(
	projektnr	SERIAL PRIMARY KEY,
	projekte 	Text,
	status		VARCHAR(100),
	erstellt_von	INTEGER	REFERENCES users(userid)
);

