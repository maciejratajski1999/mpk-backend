package org.mpk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mpk.data.LoadDataFromGTFS;
import org.mpk.entity.Route;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseDataFromTxtTest {

    @Test
    public void testFileProcessing() throws Exception {
        LoadDataFromGTFS processor = new LoadDataFromGTFS();
        Map<String, List<Map<String, String>>> data = new HashMap<>();
        Path dir = Paths.get("src/test/data");
        Files.walk(dir)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {
                    List<Map<String, String>> fileData = processor.parseTxtFile(path);
                    data.put(path.getFileName().toString(), fileData);
                });

        Assertions.assertTrue(data.containsKey("routes.txt"));
    }

    @Test
    public void testEntitiesPopulation() throws Exception{
        LoadDataFromGTFS processor = new LoadDataFromGTFS();
        Path path = Paths.get("src/test/data/routes_test.txt");
        processor.loadEntities(Route.class, path);
        Assertions.assertTrue(true);
    }
}
