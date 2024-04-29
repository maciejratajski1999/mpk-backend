package org.mpk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Route extends EntityBase {

    @Id
    @Column(name = "route_id")
    public Integer routeId;

    @Column(name = "route_type")
    public Integer routeType;

    @Column(name = "valid_from")
    public java.sql.Date validFrom;
    
}
