CREATE TABLE dc_user (
	id int8 NOT NULL,
	email varchar(255) NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT dc_user_pkey PRIMARY KEY (id),
	CONSTRAINT ukhvao49b8wh58t8b1mh15bgosa UNIQUE (username)
);

CREATE TABLE dc_user_role (
	id int8 NOT NULL,
	"role" varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT dc_user_role_pkey PRIMARY KEY (id),
	CONSTRAINT dc_user_role_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'CUSTOMER'::character varying, 'SHOP_ADMIN'::character varying, 'EMPLOYEE'::character varying])::text[])))
);

ALTER TABLE dc_user_role ADD CONSTRAINT fk13a2hq0wry9n5cw1kmnhctwcs FOREIGN KEY (user_id) REFERENCES dc_user(id);