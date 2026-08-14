package br.com.monkeyscript.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gemini.api.url:https://generativelanguage.googleapis.com/v1beta/interactions}")
    private String geminiApiUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClient( WebClient.Builder builder) {
        return builder.baseUrl(geminiApiUrl).build();
    }
}
