package org.mpk;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import io.quarkus.test.vertx.UniAsserterInterceptor;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.jupiter.api.Test;
import org.mpk.entity.Route;

import java.util.function.Supplier;

@QuarkusTest
public class RouteTest {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Test
    @RunOnVertxContext
    public void testEntity(UniAsserter asserter) {
        asserter = new UniAsserterInterceptor(asserter) {
            @Override
            protected <T> Supplier<Uni<T>> transformUni(Supplier<Uni<T>> uniSupplier) {
                return () -> Panache.withTransaction(uniSupplier);
            }
        };
        asserter.execute(() -> new Route("Testowa").persist());
        asserter.assertNotNull(() -> Route.findById("Testowa"));
//        asserter.execute(() -> Route.deleteById("Testowa"));
    }

}