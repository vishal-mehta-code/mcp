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

    public Flux<String> subscribeToEvents() {
        return webClient.get()
                .uri("/sse")
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(event -> {
                    System.out.println("Received event: " + event);
                });
    }
}