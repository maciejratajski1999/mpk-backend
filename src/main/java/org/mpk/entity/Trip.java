package org.mpk.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Trip extends PanacheEntity {
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
}