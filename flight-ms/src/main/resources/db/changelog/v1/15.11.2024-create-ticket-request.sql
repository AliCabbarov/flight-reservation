CREATE TABLE ticket_request
(
    id              BIGINT                      NOT NULL,
    created_date    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date    TIMESTAMP WITHOUT TIME ZONE,
    created_by      BIGINT                      NOT NULL,
    updated_by      BIGINT,
    status          BOOLEAN,
    created_user_id BIGINT,
    flight_id       BIGINT,
    plane_place_id  BIGINT,
    expired_date    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_ticketrequest PRIMARY KEY (id)
);