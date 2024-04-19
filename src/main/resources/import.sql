INSERT INTO Vehicle(id, vehicleID)
VALUES (nextval('Vehicle_SEQ'), 2024);
INSERT INTO Vehicle(id, vehicleID)
VALUES (nextval('Vehicle_SEQ'), 2025);
INSERT INTO Route(id, route_id, route_type, valid_from)
VALUES (nextval('Route_SEQ'), 101, 1, '2024-01-01');
INSERT INTO Route(id, route_id, route_type, valid_from)
VALUES (nextval('Route_SEQ'), 102, 2, '2024-02-01');
INSERT INTO Trip(id, trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id)
VALUES (nextval('Trip_SEQ'), 201, 101, '1 POŚWIĘTNE', 1, 301, 401);
INSERT INTO Trip(id, trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id)
VALUES (nextval('Trip_SEQ'), 202, 102, '16 KARŁOWICE', 2, 302, 402);
