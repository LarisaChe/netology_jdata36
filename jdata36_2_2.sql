	CREATE TABLE orders (
		id SERIAL PRIMARY KEY,
		dat DATE NOT NULL,
		customer_id integer REFERENCES customers (id),
		product_name VARCHAR(100) NOT NULL,
		amount numeric CHECK (price > 0)		
	);
