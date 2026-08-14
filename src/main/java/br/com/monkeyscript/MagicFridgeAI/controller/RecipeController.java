package br.com.monkeyscript.MagicFridgeAI.controller;

import br.com.monkeyscript.MagicFridgeAI.dto.FoodItemDTO;
import br.com.monkeyscript.MagicFridgeAI.service.FoodItemService;
import br.com.monkeyscript.MagicFridgeAI.service.GemineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private final FoodItemService service;
    private final GemineService gemineService;

    public RecipeController(FoodItemService service, GemineService gemineService) {
        this.service = service;
        this.gemineService = gemineService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItemDTO> foodItems = service.listarFoods();
        return gemineService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
