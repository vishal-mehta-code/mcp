package com.example.mcp.controller;

import com.example.mcp.service.DataIngestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataIngestorController {

    @Autowired
    private DataIngestorService dataIngestorService;

    @GetMapping("/ingest/data")
    public ResponseEntity<String> ingestData() {
        dataIngestorService.ingestData();
        return ResponseEntity.ok().body("Data ingested successfully");
    }
}
