CREATE TABLE verification
(
id              BIGINT                      NOT NULL,
created_date    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
updated_date    TIMESTAMP WITHOUT TIME ZONE,
created_by      BIGINT                      NOT NULL,
updated_by      BIGINT,
status          BOOLEAN,
token           VARCHAR(255),
user_id         BIGINT,
type            VARCHAR(255),
is_used         BOOLEAN,
is_expired      BOOLEAN,
expiration_time TIMESTAMP WITHOUT TIME ZONE,
CONSTRAINT pk_verification PRIMARY KEY (id)
);