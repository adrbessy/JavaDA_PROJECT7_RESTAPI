CREATE SEQUENCE public.bid_id_seq;

CREATE TABLE Bid (
  id SMALLINT NOT NULL DEFAULT nextval('public.bid_id_seq'),
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity NUMERIC(10,2) DEFAULT 0,
  ask_quantity NUMERIC(10,2) DEFAULT 0,
  bid NUMERIC(10,2) DEFAULT 0,
  ask NUMERIC(10,2) DEFAULT 0,
  benchmark VARCHAR(125),
  bid_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.bid_id_seq OWNED BY public.Bid.id;

CREATE SEQUENCE public.trade_id_seq;

CREATE TABLE Trade (
  id SMALLINT NOT NULL DEFAULT nextval('public.trade_id_seq'),
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity NUMERIC(10,2) DEFAULT 0,
  sell_quantity NUMERIC(10,2) DEFAULT 0,
  buy_price NUMERIC(10,2) DEFAULT 0,
  sell_price NUMERIC(10,2) DEFAULT 0,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.trade_id_seq OWNED BY public.Trade.id;


CREATE SEQUENCE public.curve_id_seq;

CREATE TABLE Curve_point (
  id SMALLINT NOT NULL DEFAULT nextval('public.curve_id_seq'),
  curve_id SMALLINT DEFAULT 0,
  as_of_date TIMESTAMP,
  term NUMERIC(10,2) DEFAULT 0,
  value NUMERIC(10,2) DEFAULT 0,
  creation_date TIMESTAMP ,

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.curve_id_seq OWNED BY public.Curve_point.id;


CREATE SEQUENCE public.rating_id_seq;

CREATE TABLE Rating (
  id SMALLINT NOT NULL DEFAULT nextval('public.rating_id_seq'),
  moodys_rating VARCHAR(125),
  sand_p_rating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number SMALLINT DEFAULT 0,

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.rating_id_seq OWNED BY public.Rating.id;


CREATE SEQUENCE public.rule_id_seq;

CREATE TABLE Rule (
  id SMALLINT NOT NULL DEFAULT nextval('public.rule_id_seq'),
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sql_str VARCHAR(125),
  sql_part VARCHAR(125),

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.rule_id_seq OWNED BY public.Rule.id;


CREATE SEQUENCE public.users_id_seq;

CREATE TABLE Users (
  id SMALLINT NOT NULL DEFAULT nextval('public.users_id_seq'),
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (id)
);

ALTER SEQUENCE public.users_id_seq OWNED BY public.Users.id;


INSERT INTO Users 
(fullname, username, password, role) 
VALUES 
('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN'),
('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');

INSERT INTO bid 
(account, type, bid_quantity) 
VALUES 
('account', 'type', 5),
('account2', 'type2', 10);