CREATE TABLE app."user"
(
    uuid uuid NOT NULL,
    mail text NOT NULL,
    fio text NOT NULL,
    role text NOT NULL,
    PRIMARY KEY (uuid)
);