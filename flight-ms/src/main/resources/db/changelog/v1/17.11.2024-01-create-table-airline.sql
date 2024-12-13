CREATE TABLE airline
(
    id           BIGINT                      NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_by   BIGINT                      NOT NULL,
    updated_by   BIGINT,
    status       BOOLEAN,
    name         VARCHAR(255),
    CONSTRAINT pk_airline PRIMARY KEY (id)
);

ALTER TABLE airline
    ADD CONSTRAINT uc_airline_name UNIQUE (name);