-- liquibase formatted sql

-- changeset jalig:1
CREATE TABLE paste
(
    url           VARCHAR(255) PRIMARY KEY,
    body          TEXT NOT NULL,
    creation_time timestamp    NOT NULL,
    expired_time  timestamp    NOT NULL,
    status        VARCHAR(255) NOT NULL,
    title         VARCHAR(255)
);