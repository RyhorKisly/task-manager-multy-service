CREATE TABLE app.projects
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    manager uuid NOT NULL,
    status text,
    CONSTRAINT projects_pkey PRIMARY KEY (uuid),
    CONSTRAINT project_users_foreign_key FOREIGN KEY (manager)
        REFERENCES app.users (uuid),
    CONSTRAINT projects_name_unique UNIQUE (name)
);