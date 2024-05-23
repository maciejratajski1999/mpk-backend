package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Vehicle extends PanacheEntityBase{

    @Id
    @Column(name = "vehicle_id")
    public Integer vehicleID;

    @Column(name="trip_id")
    public String tripId;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", insertable=false, updatable=false)
    public Trip trip;
}