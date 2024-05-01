package org.mpk.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.mpk.entity.Vehicle;

@ResourceProperties(path = "vehicles")
public interface VehicleResource extends PanacheEntityResource<Vehicle, Long> {
}
