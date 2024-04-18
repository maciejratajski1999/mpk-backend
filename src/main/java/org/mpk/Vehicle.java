package org.mpk;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Vehicle extends PanacheEntity {
    public int vehicleID;
}