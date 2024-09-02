CREATE TABLE dc_product (
	id int8 NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	price numeric(38, 2) NOT NULL,
	shop_ref varchar(255) NOT NULL,
	CONSTRAINT dc_product_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE dc_product_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;