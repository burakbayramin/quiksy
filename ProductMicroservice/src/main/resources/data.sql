-- Category entries
INSERT INTO category (id, description, name) VALUES (1, 'Java programming language, covering topics such as syntax, frameworks, and application development.', 'Java');
INSERT INTO category (id, description, name) VALUES (2, 'Techniques and technologies for creating websites and web applications, including frontend, backend, and full-stack development.', 'Web');
INSERT INTO category (id, description, name) VALUES (3, 'Principles and practices of object-oriented programming (OOP), emphasizing concepts like encapsulation, inheritance, and polymorphism.', 'OOP');

-- Product entries for Category 1 (Java)
INSERT INTO product (id, name, stock, price, category_id) VALUES (1, 'Android in Action, Second Edition', 23, 19.80, 1);
INSERT INTO product (id, name, stock, price, category_id) VALUES (2, 'Griffon in Action', 45, 23.50, 1);
INSERT INTO product (id, name, stock, price, category_id) VALUES (3, 'Spring Dynamic Modules in Action', 52, 49.90, 1);

-- Product entries for Category 2 (Web)
INSERT INTO product (id, name, stock, price, category_id) VALUES (4, 'Flexible Rails', 63, 26.80, 2);
INSERT INTO product (id, name, stock, price, category_id) VALUES (5, 'Sass and Compass in Action', 75, 86.30, 2);

-- Product entries for Category 3 (OOP)
INSERT INTO product (id, name, stock, price, category_id) VALUES (6, 'Object Oriented Application Frameworks', 113, 45.80, 3);
