package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Vehicle extends PanacheEntityBase{


    @Column(name = "vehicle_id")
    public Integer vehicleID;
    @Id
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    public Trip trip;
}