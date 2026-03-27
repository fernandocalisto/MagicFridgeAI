package br.com.calistofernando.MagicFridgeAI.service;

import br.com.calistofernando.MagicFridgeAI.dto.RecipeResponseDTO;
import br.com.calistofernando.MagicFridgeAI.model.FoodItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GeminiService {

    private final WebClient webClient;
    private String apiKey = System.getenv("GEMINI_KEY");
    private final ObjectMapper objectMapper;

    public Mono<RecipeResponseDTO> generateRecipe(List<FoodItemModel> foodList){

        String foodItems = foodList.stream()
                .map(item -> String.format(" - %s (%s) - Quantidade: %d, Validade: %s",
                                item.getName(), item.getFoodCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = String.format(
                "Você é o Chef virtual do aplicativo MagicFridgeAI. " +
                        "Sua missão é sugerir uma receita deliciosa usando os ingredientes disponíveis na geladeira do usuário. " +
                        "REGRA IMPORTANTE: Dê prioridade máxima para utilizar os ingredientes que estão com a data de validade mais próxima de vencer. " +
                        "Ingredientes disponíveis:\n%s\n\n" +
                        "Retorne a resposta EXCLUSIVAMENTE em formato JSON com as seguintes chaves: " +
                        "'nomeReceita' (String), " +
                        "'dificuldade' (Inteiro de 0 a 5), " +
                        "'ingredientesUsados' (Lista de Strings), " +
                        "'modoPreparo' (Lista de Strings com o passo a passo).",foodItems);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return  webClient.post().uri("/v1beta/models/gemini-3-flash-preview:generateContent")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                        String jsonIA = parts.get(0).get("text").toString();
                        try {
                            // 🔥 A grande mágica: Converte o texto JSON no seu Record Java!
                            return objectMapper.readValue(jsonIA, RecipeResponseDTO.class);
                        } catch (Exception e) {
                            // Se a IA alucinar e mandar um JSON quebrado, tratamos o erro
                            throw new RuntimeException("Erro ao converter a resposta do Chef: " + e.getMessage());
                        }
                    } else {
                        throw new RuntimeException("Nenhuma receita foi gerada.");
                    }
                });

    }

}
