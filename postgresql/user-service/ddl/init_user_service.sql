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

CREATE TABLE IF NOT EXISTS app.verification
(
    uuid uuid NOT NULL,
    token uuid NOT NULL,
    user_uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    CONSTRAINT verification_pkey PRIMARY KEY (uuid),
    CONSTRAINT verification_users_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES app.users (uuid)
)