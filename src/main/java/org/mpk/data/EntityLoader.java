package org.mpk.data;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.mpk.entity.EntityBase;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class EntityLoader {

    public Uni<Void> loadEntity(EntityBase entity, String endpoint) {
        return Uni.createFrom().emitter(em -> {
            HttpClient client = HttpClient.newHttpClient();

            String json = entity.toString();
            Log.info(json);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/" + endpoint))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        if (response.statusCode() == 200) {
                            em.complete(null);
                        } else {
                            em.fail(new RuntimeException("Failed to load entity: " + response.statusCode()));
                        }
                    })
                    .exceptionally(ex -> {
                        em.fail(ex);
                        return null;
                    });
        });
    }
}
