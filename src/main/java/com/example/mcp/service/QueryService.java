package com.example.mcp.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private EmbeddingModel embeddingModel;

    @Tool(description = "Tool to list the APIs")
    public String listApis(String query) {
        System.out.println("calling tool list_api with query: " + query);

        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(1).build());

        for (Document document : results) {
            System.out.println(document.getFormattedContent());
        }

        return results.get(0).getFormattedContent();
    }

    @Tool(description = "Tool to list the cars")
    public List<Car> listCars() {
        List<Car> list = new ArrayList<>();

        list.add(new Car("xuv", "mahindra", "blue"));
        list.add(new Car("x1", "bmw", "black"));

        return list;
    }

    @Tool(description = "Tool to list the cars by the given color")
    public List<Car> listCarsByColor(String color) {
        List<Car> list = new ArrayList<>();

        list.add(new Car("xuv", "mahindra", "blue"));
        list.add(new Car("x1", "bmw", "black"));

        return list.stream().filter(car -> car.getColor().equalsIgnoreCase(color)).toList();
    }
}
