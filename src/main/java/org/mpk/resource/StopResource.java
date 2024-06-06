package org.mpk.resource;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Stop;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/stops")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class StopResource {

    @GET
    public Uni<List<Stop>> get() {
        return Stop.listAll();
    }

    @GET
    @Path("{stopId}")
    public Uni<Stop> getSingle(String stopId) {
        return Stop.findById(stopId);
    }

}
