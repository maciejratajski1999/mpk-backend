package org.mpk.resource;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.VehiclePosition;

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

//    @POST
//    public Uni<Response> create(VehiclePosition vehiclePosition) {
//        if (vehiclePosition == null){
//            throw new WebApplicationException("VehiclePosition is null", 422);
//        }
//        return Panache.withTransaction(vehiclePosition::persist)
//                .replaceWith(Response.ok(vehiclePosition).status(CREATED)::build);
//    }

    @POST
    public Uni<Response> create(String vehiclePositionEntry) {
        return VehiclePosition.fromString(vehiclePositionEntry)
                .onItem().transformToUni(vehiclePosition -> Panache.withTransaction(vehiclePosition::persist))
                .replaceWith(Response.ok().status(CREATED)::build);
    }
}
