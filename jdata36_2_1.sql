	CREATE TABLE customers (
		id SERIAL PRIMARY KEY,
		name VARCHAR(32) NOT NULL,
		surname VARCHAR(32) NOT NULL,
		age SMALLINT NOT NULL CHECK (age >= 0),
		phone_number VARCHAR(15)
	);
