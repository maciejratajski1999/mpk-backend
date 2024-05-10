package org.mpk.entity;

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
}
