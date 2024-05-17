-- Przykłady dla encji Route
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('R1', 1, '2024-04-27');
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('A', 1, '2024-04-28');

-- Przykłady dla encji Trip
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES (13165581, 'A', 'KOSZAROWA (Szpital)', 1, 826263, 826263);
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES (2, 'R1', 'HeadSign2', 2, 200, 20);

-- Przykłady dla encji Vehicle
INSERT INTO Vehicle(vehicle_id, trip_id) VALUES (1001, 13165581);
INSERT INTO Vehicle(vehicle_id, trip_id) VALUES (1002, 13165581);
INSERT INTO VehiclePosition(pos_id, vehicle_id, pos_lat, pos_lon, timestamp) VALUES (0, 1002, 0.0, 0.0, '1999-01-01 00:01');