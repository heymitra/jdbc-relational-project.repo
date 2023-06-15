CREATE TABLE my_user
(
    id       serial primary key,
    name     varchar,
    username varchar unique,
    email    varchar unique,
    password varchar
);

CREATE TABLE category
(
    id          serial primary key,
    name        varchar unique,
    description varchar(100)
);

CREATE TABLE brand
(
    id          serial primary key,
    name        varchar unique,
    website     varchar,
    description varchar(100)
);

CREATE TABLE product
(
    id          serial primary key,
    name        varchar,
    create_date varchar,
    category_id int references category (id),
    brand_id    int references brand (id)
);

CREATE TABLE shareholder
(
    id            serial primary key,
    name          varchar,
    phone_number  varchar,
    national_code varchar unique
);

CREATE TABLE join_table
(
    shareholder_id bigint references shareholder (id),
    brand_id       bigint references brand (id)
);
