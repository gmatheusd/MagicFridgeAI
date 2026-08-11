package br.com.monkeyscript.MagicFridgeAI.controller;

import br.com.monkeyscript.MagicFridgeAI.dto.FoodItemDTO;
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
    public ResponseEntity<FoodItemDTO> criar(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO salvo = service.salvar(foodItemDTO);
        return ResponseEntity.ok(salvo);
    }

    // GET
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItemDTO>> listarFoods() {
        List<FoodItemDTO> lista = service.listarFoods();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItemDTO> listarPorId(@PathVariable Long id) {
        FoodItemDTO foodItem = service.listarPorId(id);
        return ResponseEntity.ok(foodItem);
    }

    // UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<FoodItemDTO> alterarPorId(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodAtualizado = service.alterarPorId(id, foodItemDTO);
        return ResponseEntity.ok(foodAtualizado);
    }

    // DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFoodPorId(@PathVariable Long id) {
        service.deletarFoodPorId(id);
        return ResponseEntity.ok("Item deletado com sucesso!");
    }

}
