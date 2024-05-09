package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Trip extends EntityBase {
    @Id
    @Column(name = "trip_id")
    public Integer tripId;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    public Route route;

    @Column(name = "trip_headsign")
    public String tripHeadsign;

    @Column(name = "direction_id")
    public Integer directionId;

    @Column(name = "shape_id")
    public Integer shapeId;

    @Column(name = "variant_id")
    public Integer variantId;

    @Override
    public void populateFromGTFS(Map<String, String> entry) {

    }
}