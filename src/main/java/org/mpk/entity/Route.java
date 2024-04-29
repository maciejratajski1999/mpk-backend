package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Route extends PanacheEntityBase {
    @Id
    @Column(name = "route_id")
    public Integer routeId;

    @Column(name = "route_type")
    public Integer routeType;

    @Column(name = "valid_from")
    public java.sql.Date validFrom;
}
