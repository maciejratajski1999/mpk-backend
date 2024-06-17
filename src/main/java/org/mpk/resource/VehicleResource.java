package org.mpk.resource;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Vehicle;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;
@Path("/vehicles")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class VehicleResource {

    @GET
    public Uni<List<Vehicle>> get() {
        return Vehicle.listAll();
    }

    @GET
    @Path("{vehicleId}")
    public Uni<Vehicle> getSingle(Integer vehicleId) {
        return Vehicle.findById(vehicleId);
    }


    @POST
    public Uni<Response> create(Vehicle vehicle) {
        if (vehicle == null){
            throw new WebApplicationException("Vehicle is null", 422);
        }
        return Panache.withTransaction(vehicle::persist)
                .replaceWith(Response.ok(vehicle).status(CREATED)::build);
    }
}
