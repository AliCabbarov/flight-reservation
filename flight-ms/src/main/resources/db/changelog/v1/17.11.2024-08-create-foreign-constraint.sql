ALTER TABLE flight
    ADD CONSTRAINT FK_FLIGHT_ON_PLANE FOREIGN KEY (plane_id) REFERENCES plane (id);

ALTER TABLE flight_plane_place
    ADD CONSTRAINT FK_FLIGHTPLANEPLACE_ON_FLIGHT FOREIGN KEY (flight_id) REFERENCES flight (id);

ALTER TABLE flight_plane_place
    ADD CONSTRAINT FK_FLIGHTPLANEPLACE_ON_PLANE_PLACE FOREIGN KEY (plane_place_id) REFERENCES plane_place (id);

ALTER TABLE flight_plane_place
    ADD CONSTRAINT FK_FLIGHTPLANEPLACE_ON_TICKET FOREIGN KEY (ticket_id) REFERENCES ticket (id);

ALTER TABLE plane
    ADD CONSTRAINT FK_PLANE_ON_AIRLINE FOREIGN KEY (airline_id) REFERENCES airline (id);

ALTER TABLE plane_plane_places
    ADD CONSTRAINT fk_plaplapla_on_plane FOREIGN KEY (plane_id) REFERENCES plane (id);

ALTER TABLE plane_plane_places
    ADD CONSTRAINT fk_plaplapla_on_plane_place FOREIGN KEY (plane_places_id) REFERENCES plane_place (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_FLIGHT FOREIGN KEY (flight_id) REFERENCES flight (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_TICKET_REQUEST FOREIGN KEY (ticket_request_id) REFERENCES ticket_request (id);