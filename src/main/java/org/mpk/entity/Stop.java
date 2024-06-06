package org.mpk.entity;

import io.quarkus.logging.Log;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Map;

@Entity
@Cacheable
public class Stop extends EntityBase {

    public Stop(){}

    public Stop(String stopId){
        this.stopId = stopId;
    }

    @Id
    @Column(name = "stop_id", unique = true, length = 20)
    public String stopId;

    @Column(name = "stop_code")
    public Integer stopCode;

    @Column(name = "stop_name")
    public String stopName;

    @Column(name = "stop_lat")
    public Double stopLat;

    @Column(name = "stop_lon")
    public Double stopLon;

    @Override
    public void populateFromGTFS(Map<String, String> entry){
        this.stopId= entry.get("stop_id");
        this.stopCode= Integer.valueOf(entry.get("stop_code"));
        this.stopName= entry.get("stop_name");
        this.stopLat= Double.valueOf(entry.get("stop_lat"));
        this.stopLon= Double.valueOf(entry.get("stop_lon"));
    }
}
