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

CREATE TABLE IF NOT EXISTS task.user_ref
(
    id bigint NOT NULL,
    uuid uuid NOT NULL,
    CONSTRAINT user_ref_pkey PRIMARY KEY (id)
);

CREATE TABLE task.projects
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
        REFERENCES task.user_ref (id),
    CONSTRAINT projects_name_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS task.projects_staff
(
    project_uuid uuid NOT NULL,
    staff_uuid bigint,
    CONSTRAINT projects_staff_project_uuid_fkey FOREIGN KEY (project_uuid)
        REFERENCES task.projects (uuid),
    CONSTRAINT user_ref_staff_uuid_fkey FOREIGN KEY (staff_uuid)
        REFERENCES task.user_ref (id)
);
