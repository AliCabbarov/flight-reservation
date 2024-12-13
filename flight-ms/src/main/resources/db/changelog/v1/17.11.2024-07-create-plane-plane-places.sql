CREATE TABLE plane_plane_places
(
    plane_id        BIGINT NOT NULL,
    plane_places_id BIGINT NOT NULL,
    CONSTRAINT pk_plane_planeplaces PRIMARY KEY (plane_id, plane_places_id)
);