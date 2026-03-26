package br.com.calistofernando.MagicFridgeAI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class GeminiService {

    private final WebClient webClient;
    private String apiKey = System.getenv("GEMINI_KEY");

    public Mono<String> generateRecipe(){
        String prompt = "Agora você é um chefe de cozinha e vai me sugerir receitas com base nos ingredientes que vou te passar, ok?";
    }

}
