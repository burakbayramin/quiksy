CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          description TEXT,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         stock INT NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         category_id INT REFERENCES category(id)
);
