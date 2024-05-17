package org.mpk.entity;

import io.smallrye.mutiny.Uni;
import jakarta.persistence.*;

import java.util.Map;

@Entity
public class VehiclePosition extends EntityBase {

    @Id
    @Column(name = "pos_id")
    public Integer posId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    public Vehicle vehicle;

    @Column(name = "pos_lat")
    public Double posLat;

    @Column(name = "pos_lon")
    public Double posLon;

    @Column(name = "timestamp")
    public String timestamp;


    @Override
    public void populateFromGTFS(Map<String, String> entry) {

    }

    public static Uni<VehiclePosition> fromString(String vehiclePositionEntry) {
        String[] parts = vehiclePositionEntry.split(";");
        VehiclePosition vehiclePosition = new VehiclePosition();
        vehiclePosition.posId = Integer.parseInt(parts[0]);
        vehiclePosition.posLat = Double.parseDouble(parts[1]);
        vehiclePosition.posLon = Double.parseDouble(parts[2]);
        vehiclePosition.timestamp = parts[3];
        return Vehicle.findById(Integer.parseInt(parts[0])).onItem().transform(vehicle -> {
            vehiclePosition.vehicle = (Vehicle) vehicle;
            return vehiclePosition;
        });
    }

}
