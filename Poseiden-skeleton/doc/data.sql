CREATE SEQUENCE public.bid_id_seq;

CREATE TABLE Bid (
  BidId SMALLINT NOT NULL DEFAULT nextval('public.bid_id_seq'),
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity NUMERIC(10,2),
  askQuantity NUMERIC(10,2),
  bid NUMERIC(10,2) ,
  ask NUMERIC(10,2),
  benchmark VARCHAR(125),
  bidDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidId)
);

CREATE SEQUENCE public.trade_id_seq;

CREATE TABLE Trade (
  TradeId SMALLINT NOT NULL DEFAULT nextval('public.trade_id_seq'),
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity NUMERIC(10,2),
  sellQuantity NUMERIC(10,2),
  buyPrice NUMERIC(10,2) ,
  sellPrice NUMERIC(10,2),
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

CREATE SEQUENCE public.curve_id_seq;

CREATE TABLE Curve_point (
  Id SMALLINT NOT NULL DEFAULT nextval('public.curve_id_seq'),
  CurveId SMALLINT,
  asOfDate TIMESTAMP,
  term NUMERIC(10,2) ,
  value NUMERIC(10,2) ,
  creationDate TIMESTAMP ,

  PRIMARY KEY (Id)
);

CREATE SEQUENCE public.rating_id_seq;

CREATE TABLE Rating (
  Id SMALLINT NOT NULL DEFAULT nextval('public.rating_id_seq'),
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber SMALLINT,

  PRIMARY KEY (Id)
);

CREATE SEQUENCE public.rule_id_seq;

CREATE TABLE Rule (
  Id SMALLINT NOT NULL DEFAULT nextval('public.rule_id_seq'),
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
);

CREATE SEQUENCE public.users_id_seq;

CREATE TABLE Users (
  Id SMALLINT NOT NULL DEFAULT nextval('public.users_id_seq'),
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
);

INSERT INTO Users 
(fullname, username, password, role) 
VALUES 
('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN'),
('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');