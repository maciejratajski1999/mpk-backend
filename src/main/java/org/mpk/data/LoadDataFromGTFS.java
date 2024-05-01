package org.mpk.data;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
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



@Transactional
    public <T extends EntityBase> Uni<Void> loadEntities(Class<T> entityClass, Path path) {
        return Uni.createFrom().deferred(() -> Uni.createFrom().item(parseTxtFile(path)))
                .onItem().transformToUni(parsedFileData -> {
                    Log.info("jestem tu!\n 1\n");
                    return Uni.createFrom().item(() -> {
                        Log.info("jestem tu!\n 3\n");
                        for (Map<String, String> entry : parsedFileData) {
                            Log.info("jestem tu!\n 2\n");
                            try {
                                Log.info(entry.toString());
                                T entity = entityClass.getDeclaredConstructor().newInstance();
                                entity.populateFromGTFS(entry);
                                return Panache.withTransaction(() ->
                                        Panache.getSession().invoke(session -> session.persist(entity)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    });
                }).replaceWithVoid();
    }



}

