package br.com.monkeyscript.MagicFridgeAI.controller;

import br.com.monkeyscript.MagicFridgeAI.model.FoodItem;
import br.com.monkeyscript.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @GetMapping("/boasVindas")
    public ResponseEntity<String> boasVindas() {
        return ResponseEntity.ok("Essa é minha rota de boas vindas!");
    }

    // POST
    @PostMapping("/criar")
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem foodItem) {
        FoodItem salvo = service.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }

    // GET
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItem>> listarFoods() {
        List<FoodItem> lista = service.listarFoods();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItem> listarPorId(@PathVariable Long id) {
        FoodItem foodItem = service.listarPorId(id);
        return ResponseEntity.ok(foodItem);
    }

    // UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<FoodItem> alterarPorId(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        FoodItem foodAtualizado = service.alterarPorId(id, foodItem);
        return ResponseEntity.ok(foodAtualizado);
    }

    // DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFoodPorId(@PathVariable Long id) {
        service.deletarFoodPorId(id);
        return ResponseEntity.ok("Item deletado com sucesso!");
    }

}
