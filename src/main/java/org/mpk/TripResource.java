package org.mpk;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mpk.entity.Trip;
import java.util.*;

@Path("/trips")
public class TripResource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String trip(@QueryParam("id") Integer id) {
        Trip trip = new Trip();
        trip.tripId = id;
        trip.persist();
        return "Trip #" + id;
    }

    private List<Trip> getTripsList(){
        List <Trip> tripsList = Trip.listAll();
        return tripsList;
    }

    private List<Map<String, Object>> getTripsMaps(List<Trip> tripsList){
        List<Map<String, Object>> tripsMaps = new ArrayList<>();
        for (Trip trip : tripsList){
            Map<String, Object> tripMap = new HashMap<>();
            tripMap.put("tripId", trip.tripId);
            tripMap.put("tripHeadsign", trip.tripHeadsign);
            tripMap.put("directionId", trip.directionId);
            tripMap.put("shapeId", trip.shapeId);
            tripMap.put("variantId", trip.variantId);
            tripsMaps.add(tripMap);
        }
        return tripsMaps;
    }

    @GET
    @Path("ids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(getTripsMaps(getTripsList())).build();
    }
}
