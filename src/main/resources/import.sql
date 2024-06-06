-- Przykłady dla encji Route
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('Test', 0, '2024-04-27');
INSERT INTO Route(route_id, route_type, valid_from) VALUES ('Test2', 3, '2024-04-28');

-- Przykłady dla encji Trip
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES ('001', 'Test', 'Testowa trasa 1', 1, 0001, 0001);
INSERT INTO Trip(trip_id, route_id, trip_headsign, direction_id, shape_id, variant_id) VALUES ('002', 'Test2', 'Testowa trasa 2', 2, 200, 20);

-- Przykłady dla encji Vehicle
INSERT INTO Vehicle(vehicle_id, trip_id) VALUES (1001, '001');
INSERT INTO Vehicle(vehicle_id, trip_id) VALUES (1002, '002');
INSERT INTO VehiclePosition(pos_id, vehicle_id, pos_lat, pos_lon, timestamp) VALUES (0, 1002, 0.0, 0.0, '1999-01-01 00:01');