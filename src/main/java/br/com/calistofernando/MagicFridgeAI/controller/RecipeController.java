package br.com.calistofernando.MagicFridgeAI.controller;

import br.com.calistofernando.MagicFridgeAI.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor

public class RecipeController {

    private final GeminiService geminiService;

    @GetMapping
    public Mono<ResponseEntity<String>> generateRecipe() {
        return geminiService.generateRecipe();
    }

}
