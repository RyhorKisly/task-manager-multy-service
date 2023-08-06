CREATE DATABASE user_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app."users"
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    mail text NOT NULL,
    fio text NOT NULL,
    role text NOT NULL,
    status text NOT NULL,
    password text NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_mail_unique UNIQUE (mail)
);

INSERT INTO app.users(
    uuid, dt_create, dt_update, mail, fio, role, status, password)
VALUES ('28d0390b-8004-47c7-9946-722cc55c1bfb', current_timestamp, current_timestamp, 'admin@admin.admin', 'Admin', 'ADMIN', 'ACTIVATED', '$2a$10$7QBJYpUSNMQqMX9TW8MfsewaciNqqkw6Ng5T6DqbB9o9j2.MiW6m6');

CREATE TABLE IF NOT EXISTS app.verification
(
    uuid uuid NOT NULL,
    mail text NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    CONSTRAINT verification_pkey PRIMARY KEY (uuid),
    CONSTRAINT verification_mail_unique UNIQUE (mail)
)