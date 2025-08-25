// src/main/java/com/example/mcp/McpSseClient.java
package com.example.mcp.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class McpSseClient {

    private final WebClient webClient;

    public McpSseClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://remote-mcp-server:8080").build();
    }

    // Java
    public Flux<String> subscribeToEvents(String prompt) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/sse")
                        .queryParam("prompt", prompt)
                        .build())
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(event -> {
                    System.out.println("Received event: " + event);
                });
    }
}