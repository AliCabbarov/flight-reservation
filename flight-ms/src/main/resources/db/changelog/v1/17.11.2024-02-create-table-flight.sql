CREATE TABLE flight
(
    id             BIGINT                      NOT NULL,
    created_date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date   TIMESTAMP WITHOUT TIME ZONE,
    created_by     BIGINT                      NOT NULL,
    updated_by     BIGINT,
    status         BOOLEAN,
    "from"         VARCHAR(255)                NOT NULL,
    "to"           VARCHAR(255)                NOT NULL,
    price          DECIMAL,
    ticket_count   INTEGER,
    departure_time TIMESTAMP WITHOUT TIME ZONE,
    arrival_time   TIMESTAMP WITHOUT TIME ZONE,
    plane_id       BIGINT,
    approval_state VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_flight PRIMARY KEY (id)
);

