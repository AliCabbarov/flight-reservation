insert into airline (id, created_date, updated_date, created_by, updated_by, status, name)
values  (100, '2024-12-03 14:19:21.650744', null, 0, null, true, 'THY');

insert into plane_place (id, created_date, updated_date, created_by, updated_by, status, place, row, place_number, place_type)
values  (103, '2024-12-03 14:21:32.083837', null, 0, null, true, 1, 1, 51, 'NORMAL'),
        (104, '2024-12-03 14:21:36.224420', null, 0, null, true, 1, 2, 52, 'NORMAL'),
        (105, '2024-12-03 14:21:38.004480', null, 0, null, true, 1, 3, 53, 'NORMAL'),
        (106, '2024-12-03 14:21:41.762082', null, 0, null, true, 1, 4, 54, 'NORMAL'),
        (107, '2024-12-03 14:21:44.073467', null, 0, null, true, 2, 1, 55, 'NORMAL'),
        (108, '2024-12-03 14:21:45.838683', null, 0, null, true, 2, 2, 56, 'NORMAL'),
        (109, '2024-12-03 14:21:47.173523', null, 0, null, true, 2, 3, 57, 'NORMAL'),
        (110, '2024-12-03 14:21:48.893847', null, 0, null, true, 2, 4, 58, 'NORMAL');


insert into plane (id, created_date, updated_date, created_by, updated_by, status, name, capacity, airline_id)
values  (116, '2024-12-03 14:32:20.539444', null, 0, null, true, 'Boing-777', 6, 100);

insert into plane_plane_places (plane_id, plane_places_id)
values  (116, 108),
        (116, 107),
        (116, 105),
        (116, 103),
        (116, 106),
        (116, 104);


insert into flight (id, created_date, updated_date, created_by, updated_by, status, "from", "to", price, ticket_count, departure_time, arrival_time, plane_id, approval_state, feedback_message)
values  (117, '2024-12-03 14:37:58.618474', '2024-12-03 14:46:47.187808', 0, 0, true, 'GYD', 'LHR', 564, 4, '2024-12-03 18:33:51.000000', '2024-12-03 20:33:51.000000', 116, 'APPROVED', 'okay');