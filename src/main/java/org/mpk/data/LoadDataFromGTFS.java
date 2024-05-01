package org.mpk.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class LoadDataFromGTFS {

    public Map<String, String> parseTxtFile(Path path) {
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
        return fileData;
    }
}
