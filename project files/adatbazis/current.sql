CREATE TABLE Adminisztrator
(
    adminID serial primary key,
    adminJelszo VARCHAR(255) NOT NULL,
    adminEmail VARCHAR(255) UNIQUE,
	adminNev VARCHAR(255)
);

CREATE TABLE Etterem         
(
    etteremID     serial                   primary key,
    etteremJelszo VARCHAR(255)             NOT NULL,
    telefonszam   VARCHAR(255)             NOT NULL,
    etteremNev    VARCHAR(255)             NOT NULL,
    etteremEmail  VARCHAR(255)             NOT NULL UNIQUE
);

CREATE TABLE Megrendelo              
(
    megrendeloID      serial                    primary key,
    megrendeloJelszo  VARCHAR(255)              NOT NULL,
    szulDatum         DATE                      NOT NULL,
    szallitasiCim     VARCHAR(255)              NOT NULL,
    telefonszam       VARCHAR(255)              NOT NULL,
    megrendeloNev     VARCHAR(255)              NOT NULL,
    megrendeloEmail   VARCHAR(255)              NOT NULL UNIQUE,
    egyenleg          INT

);

CREATE TABLE Termek
(
    termekID    serial        primary key,
    termekNev   VARCHAR(255)  NOT NULL  UNIQUE,
    kepUrl      VARCHAR(255)  NOT NULL,
    kategoria   VARCHAR(255)  NOT NULL,
    ar          INT           NOT NULL
);


