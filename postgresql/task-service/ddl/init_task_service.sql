CREATE DATABASE task_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION postgres;

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

CREATE TABLE IF NOT EXISTS app.user_ref_entity
(
    id bigint NOT NULL,
    uuid uuid NOT NULL,
    CONSTRAINT user_ref_entity_pkey PRIMARY KEY (id)
);

CREATE TABLE app.projects
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    manager bigint NOT NULL,
    status text,
    CONSTRAINT projects_pkey PRIMARY KEY (uuid),
    CONSTRAINT user_ref_projects_manager_fkey FOREIGN KEY (manager)
        REFERENCES app.user_ref_entity (id),
    CONSTRAINT projects_name_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS app.projects_staff
(
    project_uuid uuid NOT NULL,
    staff_uuid bigint,
    CONSTRAINT projects_staff_project_uuid_fkey FOREIGN KEY (project_uuid)
        REFERENCES app.projects (uuid),
    CONSTRAINT user_ref_staff_uuid_fkey FOREIGN KEY (staff_uuid)
        REFERENCES app.user_ref_entity (id)
);
