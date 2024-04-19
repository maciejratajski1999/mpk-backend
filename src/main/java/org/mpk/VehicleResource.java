package org.mpk;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.mpk.entity.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

@Path("/vehicle")
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

    @GET
    @Path("ids")
    @Produces(MediaType.TEXT_PLAIN)
    public String vehicleIDs() {
        List<Vehicle> vehicles = Vehicle.listAll();
        String ids = vehicles.stream().map(g -> (Long.toString(g.vehicleID)))
                .collect(Collectors.joining(", "));
        return "Registered vehicles: " + ids;
    }
}
