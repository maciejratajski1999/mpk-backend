package org.mpk;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.mpk.entity.Trip;
import java.util.List;
import java.util.stream.Collectors;

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

    @GET
    @Path("ids")
    @Produces(MediaType.TEXT_PLAIN)
    public String tripIDs() {
        List<Trip> trips = Trip.listAll();
        String ids = trips.stream().map(g -> (Long.toString(g.tripId)))
                .collect(Collectors.joining(", "));
        return "Registered trips: " + ids;
    }
}