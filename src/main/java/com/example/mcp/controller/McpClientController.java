package com.example.mcp.controller;

import com.example.mcp.client.McpSseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class McpClientController {

    @Autowired
    private McpSseClient mcpSseClient;


    // Other controller methods
    @GetMapping("/query")
    public ResponseEntity<String> queryData() {
        String query = "APIs to get a pet";
        mcpSseClient.subscribeToEvents(query)
                .doOnNext(event -> System.out.println("Received event: " + event))
                .subscribe();
        return ResponseEntity.ok().body("Subscription started. Check logs for events.");
    }
}
