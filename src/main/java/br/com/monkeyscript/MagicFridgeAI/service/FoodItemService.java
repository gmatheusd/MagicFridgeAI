package br.com.monkeyscript.MagicFridgeAI.service;

import br.com.monkeyscript.MagicFridgeAI.model.FoodItem;
import br.com.monkeyscript.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    // Criar
    public FoodItem salvar(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    // Listar
    public List<FoodItem>listarFoods() {
        return repository.findAll();
    }

    // Listar por Id
    public FoodItem listarPorId(Long id) {
        Optional<FoodItem> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

    // Alterar
    public  FoodItem alterarPorId(Long id, FoodItem foodItem) {
        Optional<FoodItem> foodExistente = repository.findById(id);
        if (foodExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        foodItem.setId(id);
        return repository.save(foodItem);
    }

    // Deletar
    public void deletarFoodPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        repository.deleteById(id);
    }

}
