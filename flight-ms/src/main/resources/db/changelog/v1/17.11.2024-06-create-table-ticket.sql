CREATE TABLE ticket
(
    id                BIGINT                      NOT NULL,
    created_date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date      TIMESTAMP WITHOUT TIME ZONE,
    created_by        BIGINT                      NOT NULL,
    updated_by        BIGINT,
    status            BOOLEAN,
    ticket_no         VARCHAR(255),
    passenger_name    VARCHAR(255),
    passenger_surname VARCHAR(255),
    email             VARCHAR(255),
    phone             VARCHAR(255),
    bought_user_id    BIGINT,
    ticket_request_id BIGINT,
    ticket_status     VARCHAR(255),
    flight_id         BIGINT,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

