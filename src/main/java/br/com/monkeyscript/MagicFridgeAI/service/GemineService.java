package br.com.monkeyscript.MagicFridgeAI.service;

import br.com.monkeyscript.MagicFridgeAI.dto.FoodItemDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GemineService {

    private final WebClient webClient;
    private String apiKey = System.getenv("GEMINI_API_KEY");

    public GemineService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemDTO> foodItems) {

        String alimentos = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(), item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu banco de dados faça uma receia com o seguints itens:\n " + alimentos;
        Map<String, Object> resquetsBody = Map.of(
                "model", "gemini-3.6-flash",
                "input", prompt
        );
        return webClient.post()
                .uri("")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", apiKey)
                .bodyValue(resquetsBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var steps = (List<Map<String, Object>>) response.get("steps");

                    if (steps != null) {
                        for (Map<String, Object> step : steps) {
                            if ("model_output".equals(step.get("type"))) {
                                var contentList = (List<Map<String, Object>>) step.get("content");
                                if (contentList != null && !contentList.isEmpty()) {
                                    return contentList.get(0).get("text").toString();
                                }
                            }
                        }
                    }

                    return "Nenhum receita foi gerada.";
                });
    }

}
