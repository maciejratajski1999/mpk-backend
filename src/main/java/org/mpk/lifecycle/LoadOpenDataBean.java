package org.mpk.lifecycle;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.hibernate.reactive.mutiny.Mutiny;
import org.mpk.data.LoadDataFromGTFS;
import org.mpk.entity.EntityBase;
import org.mpk.entity.Route;
import org.mpk.entity.Trip;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LoadOpenDataBean {


    private Map<String, Class<? extends EntityBase>> buildFileToEntityMap() {
        Map<String, Class<? extends EntityBase>> fileToEntityMap = new HashMap<>();
        fileToEntityMap.put("routes.txt", Route.class);
        fileToEntityMap.put("trips.txt", Trip.class);
        return fileToEntityMap;
    }
    @Startup
    void onStart(@Observes StartupEvent event, Mutiny.SessionFactory sf) throws Throwable {
        LoadDataFromGTFS processor = new LoadDataFromGTFS();
        Map<String, Class<? extends EntityBase>> fileToEntityMap = buildFileToEntityMap();

        for (Map.Entry<String, Class<? extends EntityBase>> entry : fileToEntityMap.entrySet()) {
            Path path = Paths.get("src/data/" + entry.getKey());
            List<Map<String, String>> parsedData = processor.parseTxtFile(path);
            for (Map<String, String> dataEntry : parsedData) {
                EntityBase entity = entry.getValue().newInstance();
                entity.populateFromGTFS(dataEntry);
                sf.withTransaction(session -> session.persist(entity)).await().indefinitely();
            }
        }
    }

}
