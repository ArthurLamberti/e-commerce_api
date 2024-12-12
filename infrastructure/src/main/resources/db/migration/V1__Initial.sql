CREATE TABLE Sellers
(
    id          VARCHAR(32)  NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    document    VARCHAR(255) NOT NULL,
    active      BOOLEAN      NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    deleted_at  DATETIME(6) NULL
);

CREATE TABLE Customers
(
    id         VARCHAR(32) PRIMARY KEY NOT NULL,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    document   VARCHAR(255)            NOT NULL,
    active     BOOLEAN                 NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL
);


CREATE TABLE Addresses
(
    id          VARCHAR(32)  NOT NULL PRIMARY KEY,
    country     VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    zip_code     VARCHAR(20)  NOT NULL,
    numeral     VARCHAR(20)  NOT NULL,
    complement  VARCHAR(255) NOT NULL,
    active      BOOLEAN      NOT NULL,
    seller_id   VARCHAR(32) NULL,
    customer_id VARCHAR(32) NULL,

    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    deleted_at  DATETIME(6) NULL,

    FOREIGN KEY (seller_id) REFERENCES Sellers (id),
    FOREIGN KEY (customer_id) REFERENCES Customers (id)
);

CREATE TABLE Items
(
    id            VARCHAR(32)  NOT NULL PRIMARY KEY,
    seller_id     VARCHAR(32)  NOT NULL,
    name          VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    image_url     VARCHAR(255) NOT NULL,
    qty_available INT          NOT NULL,
    created_at    DATETIME(6) NOT NULL,
    updated_at    DATETIME(6) NOT NULL,
    deleted_at    DATETIME(6) NULL,

    FOREIGN KEY (seller_id) REFERENCES Sellers (id)
);

CREATE TABLE Shippings
(
    id         VARCHAR(32) PRIMARY KEY NOT NULL,
    address_id VARCHAR(32)             NOT NULL,
    code       VARCHAR(255)            NOT NULL,
    price      DOUBLE                  NOT NULL,
    status     VARCHAR(255)            NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL,

    FOREIGN KEY (address_id) REFERENCES Addresses (id)
);

CREATE TABLE Purchases
(
    id          VARCHAR(32)  NOT NULL PRIMARY KEY,
    seller_id   VARCHAR(32)  NOT NULL,
    customer_id VARCHAR(32)  NOT NULL,
    totalValue  DOUBLE       NOT NULL,
    shipping_id VARCHAR(32)  NOT NULL,
    status      VARCHAR(255) NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    deleted_at  DATETIME(6) NULL,

    FOREIGN KEY (seller_id) REFERENCES Sellers (id),
    FOREIGN KEY (customer_id) REFERENCES Customers (id),
    FOREIGN KEY (shipping_id) REFERENCES Shippings (id)
);

CREATE TABLE purchase_items
(
    id          VARCHAR(32) NOT NULL PRIMARY KEY,
    purchase_id VARCHAR(32) NOT NULL,
    item_id     VARCHAR(32) NOT NULL,
    qty         INT         NOT NULL,
    price       DOUBLE      NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    deleted_at  DATETIME(6) NULL,

    FOREIGN KEY (purchase_id) REFERENCES Purchases (id),
    FOREIGN KEY (item_id) REFERENCES Items (id)
);

CREATE TABLE Reviews
(
    id          VARCHAR(32) NOT NULL PRIMARY KEY,
    customer_id VARCHAR(32) NOT NULL,
    item_id     VARCHAR(32) NOT NULL,
    description VARCHAR(300)        NOT NULL,
    rating      DOUBLE         NOT NULL NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    deleted_at  DATETIME(6) NULL,

    FOREIGN KEY (customer_id) REFERENCES Customers (id),
    FOREIGN KEY (item_id) REFERENCES Items (id)
);
