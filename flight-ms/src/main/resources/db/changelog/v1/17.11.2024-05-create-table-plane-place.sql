CREATE TABLE plane_place
(
    id           BIGINT                      NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_by   BIGINT                      NOT NULL,
    updated_by   BIGINT,
    status       BOOLEAN,
    place     INTEGER,
    row          INTEGER,
    place_number INTEGER                     NOT NULL,
    place_type   VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_planeplace PRIMARY KEY (id)
);