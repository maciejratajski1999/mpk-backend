package org.mpk.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Map;

@Entity
@Cacheable
public class Route extends EntityBase {

    public Route(){}

    public Route(String routeId){
        this.routeId = routeId;
    }

    @Id
    @Column(name = "route_id", unique = true, length = 20)
    public String routeId;

    @Column(name = "route_type")
    public Integer routeType;

    @Column(name = "valid_from")
    public String validFrom;

    @Override
    public void populateFromGTFS(Map<String, String> entry){
        this.routeId= entry.get("route_id");
        this.routeType= Integer.valueOf(entry.get("route_type"));
        this.validFrom= entry.get("valid_from");
    }
}
