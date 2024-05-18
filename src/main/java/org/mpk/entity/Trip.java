package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.Map;

@Entity
public class Trip extends EntityBase {
    @Id
    @Column(name = "trip_id")
    public String tripId;

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
    public void populateFromGTFS(Map<String, String> entry){
        this.tripId = entry.get("trip_id");
//        this.route = (Route) sf.withTransaction(session -> Route.findById(entry.get("route_id"))).await().indefinitely();
        this.tripHeadsign = entry.get("trip_headsign");
        this.directionId = Integer.valueOf(entry.get("direction_id"));
        this.shapeId = Integer.valueOf(entry.get("shape_id"));
        this.variantId = Integer.valueOf(entry.get("variant_id"));
    }

}