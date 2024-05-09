package org.mpk.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.mpk.entity.Route;

import java.util.Map;

@ApplicationScoped
public class RouteService {
    @Inject
    Mutiny.SessionFactory sf;

    public Uni<Void> createRoute(String routeId) {
        Route route = new Route(routeId);
        return sf.withTransaction(session -> session.persist(route));
    }

    public Uni<Void> createRoute(Map<String, String> entry) {
        Route route = new Route();
        route.populateFromGTFS(entry);
        return sf.withTransaction(session -> session.persist(route));
    }
}
