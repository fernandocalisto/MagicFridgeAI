package br.com.calistofernando.MagicFridgeAI.controller;

import br.com.calistofernando.MagicFridgeAI.dto.RecipeResponseDTO;
import br.com.calistofernando.MagicFridgeAI.model.FoodItemModel;
import br.com.calistofernando.MagicFridgeAI.service.FoodItemService;
import br.com.calistofernando.MagicFridgeAI.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class RecipeController {

    private final GeminiService geminiService;
    private final FoodItemService foodItemService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<RecipeResponseDTO>> generateRecipe() {
        List<FoodItemModel> foodItemModelList = foodItemService.getAllFoodItem();
        return geminiService.generateRecipe(foodItemModelList)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
