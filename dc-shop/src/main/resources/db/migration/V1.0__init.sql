CREATE TABLE dc_shop (
	id int8 NOT NULL,
	address varchar(255) NULL,
	owner_username varchar(255) NOT NULL,
	phone_number varchar(255) NULL,
	"ref" varchar(255) NOT NULL,
	shop_name varchar(255) NOT NULL,
	CONSTRAINT dc_shop_pkey PRIMARY KEY (id),
	CONSTRAINT uk7cearycuoxtaj9jmqem5dhnsu UNIQUE (ref)
);
CREATE SEQUENCE dc_shop_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE dc_shop_operator (
	id int8 NOT NULL,
	username varchar(255) NULL,
	shop_id int8 NOT NULL,
	CONSTRAINT dc_shop_operator_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX dc_shop_ref_key ON dc_shop USING btree (ref);
ALTER TABLE dc_shop_operator ADD CONSTRAINT fk70onypv5fj70dnr78ymtvljiy FOREIGN KEY (shop_id) REFERENCES dc_shop(id);

CREATE SEQUENCE dc_shop_operator_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;