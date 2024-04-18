CREATE TABLE IF NOT EXISTS app.projects_staff
(
    project_uuid uuid NOT NULL,
    staff_uuid uuid NOT NULL,
    CONSTRAINT projects_staff_pkey PRIMARY KEY (project_uuid, staff_uuid),
    CONSTRAINT projects_staff_project_uuid_fkey FOREIGN KEY (project_uuid)
    REFERENCES app.projects (uuid),
    CONSTRAINT users_staff_uuid_fkey FOREIGN KEY (staff_uuid)
    REFERENCES app.users (uuid)
    );