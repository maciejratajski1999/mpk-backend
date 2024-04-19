package org.mpk;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.mpk.entity.Route;

import java.util.List;
import java.util.stream.Collectors;

@Path("/routes")
public class RouteResource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String route(@QueryParam("id") Integer id) {
        Route route = new Route();
        route.routeId = id;
        route.persist();
        return "Route #" + id;
    }

    @GET
    @Path("ids")
    @Produces(MediaType.TEXT_PLAIN)
    public String routeIDs() {
        List<Route> routes = Route.listAll();
        String ids = routes.stream().map(g -> (Long.toString(g.routeId)))
                .collect(Collectors.joining(", "));
        return "Registered routes: " + ids;
    }
}