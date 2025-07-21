--liquibase formatted sql

--changeset Zakharov:1-drop-number_inn-users-table
ALTER TABLE users DROP COLUMN number_inn;
--rollback ALTER TABLE users ADD COLUMN number_inn VARCHAR(12) NOT NULL UNIQUE;

--changeset Zakharov:2-create-table-requisites
CREATE TABLE requisites (
    user_id UUID PRIMARY KEY REFERENCES users(id),
    account_number VARCHAR(20) NOT NULL UNIQUE,
    bic VARCHAR(9) NOT NULL DEFAULT '044525225',
    correspondent_account VARCHAR(20) NOT NULL DEFAULT '30101810700000000187',
    number_inn VARCHAR(12) NOT NULL UNIQUE,
    kpp VARCHAR(9) NOT NULL DEFAULT '773601001',
    kbk VARCHAR(20)
);
--rollback DROP TABLE requisites CASCADE;