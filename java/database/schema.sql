BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, potlucks, dishes, users_friends CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(50),
	first_name varchar(50),
	last_name varchar(50),
	dietary_restrictions varchar(200),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE potlucks (
	potluck_id SERIAL,
	event_name varchar(50) NOT NULL,
	description varchar(500),
	event_date DATE NOT NULL,
	event_time TIME NOT NULL,
	user_id int,
	potluck_dietary_restrictions varchar[],
	is_recurring boolean NOT NULL,
	frequency_days int,
	location varchar(200),
	is_private boolean NOT NULL,
	is_completed boolean NOT NULL,
	CONSTRAINT pk_potlucks PRIMARY KEY (potluck_id),
	CONSTRAINT fk_potlucks_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE dishes (
	dish_id SERIAL,
	dish_name varchar(100) NOT NULL,
	dish_dietary_restrictions varchar(200) NOT NULL,
	category varchar(50) NOT NULL,
	servings int NOT NULL,
	recipe varchar,
	dish_creator int,

	CONSTRAINT pk_dishes PRIMARY KEY (dish_id)
);

CREATE TABLE potluck_user (
	potluck_id int NOT NULL,
	user_id int NOT NULL,

	CONSTRAINT pk_potluck_user PRIMARY KEY (potluck_id, user_id),
	CONSTRAINT fk_potluck_user_potluck FOREIGN KEY (potluck_id) REFERENCES potlucks (potluck_id),
	CONSTRAINT fk_potluck_user_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE potluck_dish(
	potluck_id int NOT NULL,
	dish_id int NOT NULL,
	dish_rating int,

	CONSTRAINT pk_potluck_dish PRIMARY KEY (potluck_id, dish_id),
	CONSTRAINT fk_potluck_dish_potluck FOREIGN KEY (potluck_id) REFERENCES potlucks (potluck_id),
	CONSTRAINT fk_potluck_dish_dish FOREIGN KEY (dish_id) REFERENCES dishes (dish_id)
);
CREATE TABLE users_friends (
	user_id int,
	friend_id SERIAL,
	friend_first_name varchar (50),
	friend_last_name varchar (50),
	friend_email_address varchar(50) NOT NULL UNIQUE,
	CONSTRAINT PK_users_friends PRIMARY KEY (user_id,friend_id),
	CONSTRAINT FK_users_users_friends FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE potluck_guests (
	potluck_id int,
	guest_id SERIAL,
	guest_first_name varchar (50),
	guest_last_name varchar (50),
	guest_email_address varchar(50) NOT NULL UNIQUE,
	guest_already_registered boolean default false,
	CONSTRAINT PK_potluck_guests PRIMARY KEY (potluck_id, guest_id),
	CONSTRAINT FK_potlucks_potluck_guests FOREIGN KEY (potluck_id) REFERENCES potlucks(potluck_id));



COMMIT TRANSACTION;
