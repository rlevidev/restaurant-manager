INSERT INTO tables (number, description, capacity)
VALUES
    (1, 'Table 1', 4),
    (2, 'Table 2', 4),
    (3, 'Table 3', 2),
    (4, 'Table 4', 6),
    (5, 'Table 5', 8);

INSERT INTO categories_products (name)
VALUES
    ('Appetizers'),
    ('Main Courses'),
    ('Beverages'),
    ('Desserts');

INSERT INTO products (categories_id, name, description, price, preparation_time_minutes)
SELECT id, 'French fries', 'Portion of french fries', 19.90, 20
FROM categories_products WHERE name = 'Appetizers';

INSERT INTO products (categories_id, name, description, price, preparation_time_minutes)
SELECT id, 'Chef Hamburger', 'Chef special hamburger', 39.90, 30
FROM categories_products WHERE name = 'Main Courses';

INSERT INTO products (categories_id, name, description, price, preparation_time_minutes)
SELECT id, 'Pepsi', '2-liter Pepsi', 10.90, 5
FROM categories_products WHERE name = 'Beverages';

INSERT INTO products (categories_id, name, description, price, preparation_time_minutes)
SELECT id, 'Pudding', 'A plate with pudding', 14.90, 10
FROM categories_products WHERE name = 'Desserts';
