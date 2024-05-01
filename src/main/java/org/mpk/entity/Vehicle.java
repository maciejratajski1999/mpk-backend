package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicle extends PanacheEntityBase{


    @Column(name = "vehicle_id")
    public Integer vehicleID;
    @Id
    @Column(name="id")
    private Long id;
}