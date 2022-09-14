CREATE TABLE Adminisztrator
(
    adminID int not null,
    adminJelszo VARCHAR(255) NOT NULL,
    adminEmail VARCHAR(255) UNIQUE,
	  adminNev VARCHAR(255)
);

ALTER TABLE Adminisztrator  --auto increment adminID-hez && adminID primary key-� v�lt�sa
  ADD (
    CONSTRAINT Adminisztrator_pk PRIMARY KEY (adminID)
  );


CREATE SEQUENCE en_seq;

CREATE OR REPLACE TRIGGER mytrigger
  BEFORE INSERT ON Adminisztrator
  FOR EACH ROW
BEGIN
  SELECT en_seq.nextval
  INTO :new.adminID
  FROM dual;
END;

CREATE TABLE Etterem             -- etterem tabla
(
    etteremID     INT                      NOT NULL,
    etteremJelszo VARCHAR(255)             NOT NULL,
    telefonszam   VARCHAR(255)             NOT NULL,
    etteremNev    VARCHAR(255)             NOT NULL,
    etteremEmail  VARCHAR(255)             NOT NULL UNIQUE,
);


CREATE TABLE Megrendelo              -- megrendelo tabla
(
    megrendeloID     INT                      NOT NULL,
    megrendeloJelszo VARCHAR(255)             NOT NULL,
    szulDatum         DATE                     NOT NULL,
    szallitasiCim     VARCHAR(255)             NOT NULL,
    telefonszam       VARCHAR(255)             NOT NULL,
    megrendeloNev    VARCHAR(255)             NOT NULL,
    megrendeloEmail  VARCHAR(255)             NOT NULL UNIQUE,
    egyenleg          INT

);

ALTER TABLE Megrendelo               --auto increment megrendeloID-hez. &&megrendeloID pk-ve valtasa
  ADD (
    CONSTRAINT megrendelo_pk PRIMARY KEY (megrendeloID)
  );


CREATE SEQUENCE en_seq2;

CREATE OR REPLACE TRIGGER mytrigger2
  BEFORE INSERT ON Megrendelo
  FOR EACH ROW
BEGIN
  SELECT en_seq2.nextval
  INTO :new.megrendeloID
  FROM dual;
END;

CREATE TABLE Termek
(
    termekID    INT           NOT NULL,
    termekNev   VARCHAR(255)  NOT NULL  UNIQUE,
    kepUrl      VARCHAR(255)  NOT NULL,
    kategoria   VARCHAR(255)  NOT NULL,
    ar          INT           NOT NULL
);

ALTER TABLE Termek       --auto increment termekIdhez && termekKod primary key-e valtoztatasa
  ADD (
    CONSTRAINT termek_pk PRIMARY KEY (termekID)
  );


CREATE SEQUENCE en_seq3;

CREATE OR REPLACE TRIGGER mytrigger3
  BEFORE INSERT ON Termek
  FOR EACH ROW
BEGIN
  SELECT en_seq3.nextval
  INTO :new.termekID
  FROM dual;
END;



CREATE TABLE megrendeloModositas                -- admin es megrendelo kozotti kapcsolat, ki mit modositott
	modositasDatuma      DATE            NOT NULL,
	mitModositott    VARCHAR(255)    NOT NULL, 
	adminID int REFERENCES Adminisztrator(adminID),
	megrendeloID int REFERENCES Megrendelo(megrendeloID)

);

CREATE TABLE adminModositas                -- admin es admin kozotti kapcsolat, tudjuk mikor kit modositott..
(
	modositasDatuma     DATE            NOT NULL,
	mitModositott       VARCHAR(255)    NOT NULL, 
	adminID             int REFERENCES Adminisztrator(adminID),
	modositottAdminID   int REFERENCES Adminisztrator(adminID)

);

CREATE TABLE etteremModositas                -- admin es etterem kozotti kapcsolat, ki mit modositott..
(
	modositasDatuma     DATE                 NOT NULL,
	mitModositott       VARCHAR(255)         NOT NULL, 
	adminID             int REFERENCES Adminisztrator(adminID),
	etteremID        int REFERENCES Etterem(etteremID)

);
