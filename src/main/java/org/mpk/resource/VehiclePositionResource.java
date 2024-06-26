package org.mpk.resource;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Vehicle;
import org.mpk.entity.VehiclePosition;

import java.util.Arrays;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/vehiclepositions")
@ApplicationScoped
@Produces("application/json")
@Consumes("text/plain")
public class VehiclePositionResource {

    @GET
    public Uni<List<VehiclePosition>> get() {
        return VehiclePosition.listAll();
    }

    @GET
    @Path("{posId}")
    public Uni<VehiclePosition> getSingle(Integer posId) {
        return VehiclePosition.findById(posId);
    }

    @POST
    public Uni<Response> create(String vehiclePositionEntry) {
        return VehiclePosition.fromString(vehiclePositionEntry)
                .onItem().transformToUni(vehiclePosition -> Panache.withTransaction(vehiclePosition::persist))
                .replaceWith(Response.ok().status(CREATED)::build);
    }

    @GET
    @Path("/latest")
    public Uni<List<VehiclePosition>> getLatestPositionsForRoutes(@QueryParam("routeIds") String routeIdsString) {
        List<String> routeIds = Arrays.asList(routeIdsString.split(","));
        //testowa odpowiedź
        if (routeIds.isEmpty()) {
            return VehiclePosition.findById(0)
                    .onItem().transform(testPosition -> List.of((VehiclePosition) testPosition));
        }
        return Vehicle.list("trip.route.routeId in ?1", routeIds)
                .onItem().transformToMulti(vehicles -> Multi.createFrom().iterable(vehicles))
                .onItem().castTo(Vehicle.class)
                .onItem().transformToUniAndConcatenate(vehicle ->
                    VehiclePosition.find("vehicle.vehicleID = ?1 ORDER BY timestamp DESC", vehicle.vehicleID)
                    .firstResult())
                .onItem().castTo(VehiclePosition.class)
                .collect().asList();
    }


}