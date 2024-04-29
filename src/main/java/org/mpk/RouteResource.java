package org.mpk;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.mpk.entity.Route;

@ResourceProperties(path = "routes")
public interface RouteResource extends PanacheEntityResource<Route, Long> {
}
