package org.mpk;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Vehicle;
import java.util.*;

@Path("/vehicles")
public class VehicleResource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String vehicle(@QueryParam("id") Integer id) {
        Vehicle vehicle = new Vehicle();
        vehicle.vehicleID = id;
        vehicle.persist();
        return "Vehicle #" + id;
    }

    private List<Vehicle> getVehiclesList(){
        List <Vehicle> vehiclesList = Vehicle.listAll();
        return vehiclesList;
    }

    private List<Map<String, Object>> getVehiclesMaps(List<Vehicle> vehiclesList){
        List<Map<String, Object>> vehiclesMaps = new ArrayList<>();
        for (Vehicle vehicle : vehiclesList){
            Map<String, Object> vehicleMap = new HashMap<>();
            vehicleMap.put("vehicleID", vehicle.vehicleID);
            vehiclesMaps.add(vehicleMap);
        }
        return vehiclesMaps;
    }

    @GET
    @Path("ids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(getVehiclesMaps(getVehiclesList())).build();
    }
}
