package com.example.mcp.controller;

import com.example.mcp.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/query")
    public ResponseEntity<String> queryData() {
        String query = "APIs to get a pet";
        String result = queryService.listApis(query);
        return ResponseEntity.ok().body("Data queried successfully with result : " + result);
    }
}
