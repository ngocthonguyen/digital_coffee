CREATE TABLE dc_order (
	id int8 NOT NULL,
	customer_username varchar(255) NOT NULL,
	order_date timestamp(6) NOT NULL,
	shop_ref varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT dc_order_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE dc_order_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE dc_order_line (
	id int8 NOT NULL,
	nb int4 NOT NULL,
	product_id int8 NOT NULL,
	order_id int8 NOT NULL,
	CONSTRAINT dc_order_line_pkey PRIMARY KEY (id)
);
ALTER TABLE dc_order_line ADD CONSTRAINT fka2imf2toqn5wh387spc3w7bap FOREIGN KEY (order_id) REFERENCES dc_order(id);
CREATE SEQUENCE dc_order_line_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE dc_notification (
	id int8 NOT NULL,
	email varchar(255) NULL,
	mobile_no varchar(255) NULL,
	notification_type varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT dc_notification_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE dc_notification_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;