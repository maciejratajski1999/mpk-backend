package org.mpk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import java.util.Map;

@Entity
public class Route extends EntityBase {


    @Id
    @Column(name = "route_id")
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


