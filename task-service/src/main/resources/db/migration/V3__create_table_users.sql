CREATE TABLE IF NOT EXISTS app.users
(
    uuid uuid NOT NULL,
    CONSTRAINT user_ref_pkey PRIMARY KEY (uuid)
    );