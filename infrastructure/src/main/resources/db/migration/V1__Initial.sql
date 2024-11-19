
CREATE TABLE addresses (
    id CHAR(32) NOT NULL PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    complement VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL
);