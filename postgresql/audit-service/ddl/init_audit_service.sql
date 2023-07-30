CREATE DATABASE audit_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app."user"
(
    uuid uuid NOT NULL,
    mail text NOT NULL,
    fio text NOT NULL,
    role text NOT NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE app."audit"
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    user_uuid uuid NOT NULL,
    text text,
    type text NOT NULL,
    id text NOT NULL,
    PRIMARY KEY (uuid),
    CONSTRAINT audit_uuid_unique UNIQUE (uuid),
    CONSTRAINT audit_user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES app.user (uuid)
);