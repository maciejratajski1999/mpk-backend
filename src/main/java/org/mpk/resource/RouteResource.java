package org.mpk.resource;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Route;
import org.mpk.entity.VehiclePosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/routes")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RouteResource{

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
        if (route == null){
            throw new WebApplicationException("Route route is null", 422);
        }
        return Panache.withTransaction(route::persist)
                .replaceWith(Response.ok(route).status(CREATED)::build);
    }


//    @GET
//    @Path("/latest-positions")
//    public Uni<List<VehiclePosition>> getLatestPositions(@QueryParam("routeIds") List<String> routeIds) {
//          return Panache
//                .withTransaction(() -> VehiclePosition.find("vehicle.trip.route.routeId IN ?1 ORDER BY timestamp DESC", routeIds).list())
//                .onItem().transform(vehiclePositions -> {
//                    Map<String, VehiclePosition> latestPositionsByRoute = new HashMap<>();
//                    for (VehiclePosition position : vehiclePositions) {
//                        String routeId =  position.vehicle.trip.route.routeId;
//                        if (!latestPositionsByRoute.containsKey(routeId)) {
//                            latestPositionsByRoute.put(routeId, position);
//                        }
//                    }
//                    return new ArrayList<>(latestPositionsByRoute.values());
//                });
//    }


}
