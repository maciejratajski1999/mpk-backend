package org.mpk;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.mpk.entity.Route;

@ResourceProperties(path = "routes")
public interface RouteResource extends PanacheEntityResource<Route, Long> {

    @GET
    @Path("/hello")
    @Produces("text/plain")
    default String hello(){
        return Route.hello;
    }
}
