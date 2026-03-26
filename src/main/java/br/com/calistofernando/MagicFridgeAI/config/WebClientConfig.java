package br.com.calistofernando.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class WebClientConfig {

    @Value("${geminiApiUrl:https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=}")
    private String geminiAPIURL;

    @Bean
    public WebClient webClient (WebClient.Builder builder) {
        return builder.baseUrl(geminiAPIURL).build();
    }
}
