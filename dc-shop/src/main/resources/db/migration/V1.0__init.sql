
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

CREATE TABLE dc_shop_operator (
	id int8 NOT NULL,
	username varchar(255) NULL,
	shop_id int8 NOT NULL,
	CONSTRAINT dc_shop_operator_pkey PRIMARY KEY (id)
);

ALTER TABLE dc_shop_operator ADD CONSTRAINT fk70onypv5fj70dnr78ymtvljiy FOREIGN KEY (shop_id) REFERENCES public.dc_shop(id);