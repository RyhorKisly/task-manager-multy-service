CREATE DATABASE task_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.projects
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    manager uuid NOT NULL,
    status text,
    PRIMARY KEY (uuid),
    CONSTRAINT projects_name_unique UNIQUE (name),
    constraint projects_status_check
        check ((status)::text = ANY ((ARRAY ['ACTIVE'::character varying, 'ARCHIVED'::character varying])::text[]))
);

CREATE TABLE IF NOT EXISTS app.projects_staff
(
    project_uuid uuid NOT NULL,
    staff_uuid uuid,
    CONSTRAINT projects_staff_project_uuid_fkey FOREIGN KEY (project_uuid)
        REFERENCES app.projects (uuid)
)