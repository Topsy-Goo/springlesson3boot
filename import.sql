DROP TABLE products IF EXISTS;

CREATE TABLE IF NOT EXISTS products
	(id IDENTITY UNIQUE NOT NULL,
	 title VARCHAR(255),
	 measure VARCHAR(255),
	 price DECIMAL(12,2),
	 PRIMARY KEY (id));

INSERT INTO products (title, measure, price) VALUES
	('Сыр',		'1 кг',	4.50),
	('Молоко',	'1 л',	0.80),
	('Чай',		'200 г',2.50),
	('Кофе',	'250 г',2.80),
	('Шоколад',	'100 г',1.00);
