CREATE TABLE app.tasks
(
    uuid uuid,
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    project uuid NOT NULL,
    title text NOT NULL,
    description text,
    status text,
    implementer uuid,
    PRIMARY KEY (uuid),
    CONSTRAINT tasks_title_unique UNIQUE (title)
);