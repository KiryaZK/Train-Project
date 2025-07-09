--liquibase formatted sql

--changeset Zakharov:1-create-table-users
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    number_inn VARCHAR(12) NOT NULL UNIQUE,
    snils VARCHAR(11) NOT NULL UNIQUE,
    passport_number VARCHAR(10) NOT NULL UNIQUE,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL
);
--rollback DROP TABLE users CASCADE;

--changeset Zakharov:2-create-table-user_roles
CREATE TABLE user_roles (
    user_id UUID NOT NULL REFERENCES users(id),
    role VARCHAR(20) NOT NULL
);
--rollback DROP TABLE user_roles CASCADE;