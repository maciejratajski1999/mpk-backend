package org.mpk.resource;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Trip;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/trips")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TripResource {

    @GET
    public Uni<List<Trip>> get() {
        return Trip.listAll();
    }

    @GET
    @Path("{tripId}")
    public Uni<Trip> getSingle(Integer tripId) {
        return Trip.findById(tripId);
    }

    @POST
    public Uni<Response> create(Trip trip) {
        if (trip == null){
            throw new WebApplicationException("Trip is null", 422);
        }
        return Panache.withTransaction(trip::persist)
                .replaceWith(Response.ok(trip).status(CREATED)::build);
    }
}
