CREATE TABLE flight_plane_place
(
    id             BIGINT                      NOT NULL,
    created_date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date   TIMESTAMP WITHOUT TIME ZONE,
    created_by     BIGINT                      NOT NULL,
    updated_by     BIGINT,
    status         BOOLEAN,
    flight_id      BIGINT,
    plane_place_id BIGINT,
    ticket_id      BIGINT,
    place_status   VARCHAR(255),
    CONSTRAINT pk_flightplaneplace PRIMARY KEY (id)
);

