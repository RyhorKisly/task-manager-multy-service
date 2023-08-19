CREATE SCHEMA task;

CREATE TABLE task.tasks
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

CREATE TABLE IF NOT EXISTS task.users
(
    uuid uuid NOT NULL,
    CONSTRAINT user_ref_pkey PRIMARY KEY (uuid)
);

CREATE TABLE task.projects
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
        REFERENCES task.users (uuid),
    CONSTRAINT projects_name_unique UNIQUE (name)
);



CREATE TABLE IF NOT EXISTS task.projects_staff
(
    project_uuid uuid NOT NULL,
    staff_uuid uuid NOT NULL,
    CONSTRAINT projects_staff_pkey PRIMARY KEY (project_uuid, staff_uuid),
    CONSTRAINT projects_staff_project_uuid_fkey FOREIGN KEY (project_uuid)
        REFERENCES task.projects (uuid),
    CONSTRAINT users_staff_uuid_fkey FOREIGN KEY (staff_uuid)
        REFERENCES task.users (uuid)
);
