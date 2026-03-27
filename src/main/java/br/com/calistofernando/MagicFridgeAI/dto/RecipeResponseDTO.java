package br.com.calistofernando.MagicFridgeAI.dto;

import java.util.List;

public record RecipeResponseDTO(
        String nomeReceita,
        Integer dificuldade,
        List<String> ingredientesUsados,
        List<String> modoPreparo
) {
}