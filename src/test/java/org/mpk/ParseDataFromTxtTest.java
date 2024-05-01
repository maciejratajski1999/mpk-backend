package org.mpk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LoadDataFromGTFS {

    @Test
    public void testFileProcessing() throws Exception {
        Map<String, Map<String, String>> data = new HashMap<>();
        Path dir = Paths.get("src/test/data");
        Files.walk(dir)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {
                    Map<String, String> fileData = new HashMap<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] fields = line.split(",");
                            fileData.put(fields[0], fields[1]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    data.put(path.getFileName().toString(), fileData);
                });

        // Tutaj możesz dodać asercje sprawdzające, czy dane zostały poprawnie przetworzone
        // Na przykład:
        Assertions.assertTrue(data.containsKey("routes.txt"));
    }
}
