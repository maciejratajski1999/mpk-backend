package org.mpk.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mpk.entity.Route;

@ApplicationScoped
public class RouteRepository implements PanacheRepository<Route> {

}
