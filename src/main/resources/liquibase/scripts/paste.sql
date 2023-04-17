-- liquibase formatted sql

-- changeset jalig:1
CREATE TABLE paste
(
    url           VARCHAR(255) PRIMARY KEY,
    body          VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP    NOT NULL,
    expired_time  TIMESTAMP    NOT NULL,
    status        VARCHAR(255) NOT NULL,
    title         VARCHAR(255)
);