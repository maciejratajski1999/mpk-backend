package org.mpk.data;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hibernate.reactive.mutiny.Mutiny;
import org.mpk.entity.EntityBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LoadDataFromGTFS {

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



//@Transactional
    public <T extends EntityBase> Uni<Void> loadEntities(Class<T> entityClass, Path path) {
        List<Uni<Void>> unis = new ArrayList<>();
        List<Map<String, String>> parsedData = parseTxtFile(path);
        for (Map<String, String> entry : parsedData) {
            try {
                Log.info(entry.toString());
                T entity = entityClass.getDeclaredConstructor().newInstance();
                entity.populateFromGTFS(entry);
                unis.add(sf.withTransaction(session -> session.persist(entity).onItem().ignore().andContinueWithNull()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unis.isEmpty() ? Uni.createFrom().voidItem() : Uni.combine().all().unis(unis).discardItems();
    }






}

