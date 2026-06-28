--------------------------------------- TABLES ----------------------------------------

CREATE TABLE tables (
    id UUID PRIMARY KEY,
    number INTEGER NOT NULL UNIQUE,
    description VARCHAR(255),
    capacity INTEGER NOT NULL DEFAULT 4,
    status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    CHECK (status IN ('AVAILABLE', 'BOOKED', 'UNAVAILABLE'))
);

--------------------------------------- PRODUCTS ----------------------------------------

CREATE TABLE categories_products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE products (
    id UUID PRIMARY KEY,
    categories_id UUID NOT NULL REFERENCES categories_products(id),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    available BOOLEAN NOT NULL DEFAULT TRUE,
    preparation_time_minutes INTEGER,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_categories ON products(categories_id);

--------------------------------------- ORDERS ----------------------------------------

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    table_id BIGINT NOT NULL REFERENCES tables(id),
    date_opening TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_closing TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    observations TEXT,
    CHECK (status IN ('OPEN', 'CLOSED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED', 'DELIVERED'))
);

CREATE INDEX idx_orders_table ON orders(table_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_date_opening ON orders(date_opening);

CREATE TABLE order_itens (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders(id),
    product_id UUID NOT NULL REFERENCES products(id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    price_unit DECIMAL(10, 2) NOT NULL CHECK (price_unit >= 0),
    total_price DECIMAL(10, 2) NOT NULL CHECK (total_price >= 0),
    observations TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    CHECK (status IN ('PENDING', 'IN PREPARATION', 'READY',  'DELIVERED', 'CANCELLED'))
);

CREATE INDEX idx_order_itens_order ON order_itens(order_id);
CREATE INDEX idx_order_itens_product ON order_itens(product_id);
CREATE INDEX idx_order_itens_status ON order_itens(status);

--------------------------------------- PAYMENTS ----------------------------------------

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders(id),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
    payment_method VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    external_transaction_code VARCHAR(100),
    payment_date TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CHECK(payment_method IN ('CREDIT_CARD', 'DEBIT_CARD', 'PIX', 'CASH'))
    CHECK(status IN ('PENDING', 'APPROVED', 'REJECTED', 'CANCELED'))
);

CREATE INDEX idx_payments_order ON payments(order_id);
CREATE INDEX idx_payments_status ON payments(status);

--------------------------------------- ORDER CLOSING ----------------------------------------

CREATE TABLE order_closing (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE REFERENCES orders(id),
    subtotal DECIMAL(10, 2) NOT NULL CHECK (subtotal >= 0),
    service_charge DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (service_charge >= 0),
    discount DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (discount >= 0),
    delivery_fee DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (delivery_fee >= 0),
    total DECIMAL(10, 2) NOT NULL CHECK (total >= 0),
    closed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);