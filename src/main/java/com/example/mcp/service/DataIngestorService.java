package com.example.mcp.service;

import com.example.mcp.controller.DataIngestorController;
import com.google.gson.Gson;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataIngestorService {

    @Autowired
    private VectorStore vectorStore;

    public void ingestData() {
        Yaml yaml = new Yaml();

        InputStream inputStream = DataIngestorController.class.getResourceAsStream("/specs/petstore.yaml");
        Map<String, Object> data = yaml.load(inputStream);

        Map<String, Object> paths = (Map<String, Object>) data.get("paths");
        Map<String, Object> info = (Map<String, Object>) data.get("info");

        List<Document> documents = new ArrayList<>();
        for (String p : paths.keySet()) {
            Map<String, Object> methods = (Map<String, Object>) paths.get(p);
            for (String m : methods.keySet()) {
                Map<String, Object> path = (Map<String, Object>) methods.get(m);
                path.put("method", m);
                path.put("path", p);
                path.put("api-description", info.getOrDefault("description", null));

                Map<String, Object> contact = (Map<String, Object>) info.get("contact");
                path.put("api-owner", contact.get("email"));
                documents.add(new Document(new Gson().toJson(path)));
            }
        }

        vectorStore.add(documents);
    }
}
