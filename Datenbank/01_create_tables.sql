drop table if exists users cascade;
drop table if exists karten cascade;
drop table if exists popups cascade;
drop table if exists gegenstaende cascade;
drop table if exists projekte cascade;



create table users(
	userid		SERIAL PRIMARY KEY,
	vorname		VARCHAR(100),
	nachname	VARCHAR(100),
	geburtstag	VARCHAR(100),
	strasse		VARCHAR(200),
	hausnummer	INTEGER,
	plz 		VARCHAR(30),
	ort		VARCHAR(100),
	username	VARCHAR(100),
	passwort	VARCHAR(100)
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
	beschreibung	Text,
	name 		Text,
	status		boolean,
	file 		bytea,
	erstellt_von	date
);

