package org.mpk.data;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.Response;
import org.hibernate.reactive.mutiny.Mutiny;
import org.mpk.entity.EntityBase;
import org.mpk.entity.Route;
import org.mpk.repository.BaseRepository;
import org.mpk.repository.RouteRepository;
import org.mpk.resource.RouteResource;
import org.mpk.service.RouteService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.quarkus.hibernate.reactive.panache.Panache.withTransaction;

@ApplicationScoped
public class LoadDataFromGTFS {

    @Inject
    RouteRepository repository;

    @Inject
    RouteService service;

    @Inject
    Mutiny.SessionFactory sf;

    public List<Map<String, String>> parseTxtFile(Path path) {
        List<Map<String, String>> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            String[] headers = reader.readLine().split(",");
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length && i < fields.length; i++) {
                    row.put(headers[i], fields[i]);
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public <T extends EntityBase> void loadEntities(Class<T> entityClass, Path path, String endpoint) {
        List<Uni<T>> unis = new ArrayList<>();
        List<Map<String, String>> parsedData = parseTxtFile(path);
        for (Map<String, String> entry : parsedData) {
            service.createRoute(entry);
        }
    }


}

