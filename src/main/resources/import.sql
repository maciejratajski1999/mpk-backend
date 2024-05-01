-- Przykłady dla encji Route
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('R1', 1, '2024-04-27');
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('R2', 2, '2024-04-28');

-- Przykłady dla encji Trip
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES (1, 'R1', 'HeadSign1', 1, 100, 10);
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES (2, 'R2', 'HeadSign2', 2, 200, 20);

-- Przykłady dla encji Vehicle
INSERT INTO Vehicle(id, vehicle_id) VALUES (1, 1001);
INSERT INTO Vehicle(id, vehicle_id) VALUES (2, 1002);
