CREATE TABLE plane
(
    id           BIGINT                      NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_by   BIGINT                      NOT NULL,
    updated_by   BIGINT,
    status       BOOLEAN,
    name         VARCHAR(255),
    capacity     INTEGER,
    airline_id   BIGINT,
    CONSTRAINT pk_plane PRIMARY KEY (id)
);

