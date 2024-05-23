package org.mpk.resource;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Route;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/routes")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RouteResource {

    @GET
    public Uni<List<Route>> get() {
        return Route.listAll();
    }

    @GET
    @Path("{routeId}")
    public Uni<Route> getSingle(String routeId) {
        return Route.findById(routeId);
    }

    @POST
    public Uni<Response> create(Route route) {
        if (route == null) {
            throw new WebApplicationException("Route route is null", 422);
        }
        return Panache.withTransaction(route::persist)
                .replaceWith(Response.ok(route).status(CREATED)::build);
    }

}
