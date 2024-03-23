CREATE TABLE dc_customer (
	id int8 NOT NULL,
	dob date NULL,
	phone_number varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT dc_customer_pkey PRIMARY KEY (id),
	CONSTRAINT ukc7k0n2o93vi46drb8fievqfsb UNIQUE (username)
);
CREATE UNIQUE INDEX dc_customer_username_key ON dc_customer USING btree (username);
CREATE SEQUENCE dc_customer_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;