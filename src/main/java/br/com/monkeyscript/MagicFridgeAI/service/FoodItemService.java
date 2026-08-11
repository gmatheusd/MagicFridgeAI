package br.com.monkeyscript.MagicFridgeAI.service;

import br.com.monkeyscript.MagicFridgeAI.dto.FoodItemDTO;
import br.com.monkeyscript.MagicFridgeAI.mapper.FoodItemMapper;
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
    private final FoodItemMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Criar
    public FoodItemDTO salvar(FoodItemDTO foodItemDTO) {
        FoodItem entity = mapper.toEntity(foodItemDTO);
        FoodItem salvo = repository.save(entity);
        return mapper.toDTO(salvo);
    }

    // Listar
    public List<FoodItemDTO>listarFoods() {
        List<FoodItem> lista = repository.findAll();
        return lista.stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Listar por Id
    public FoodItemDTO listarPorId(Long id) {
        Optional<FoodItem> foodPorId = repository.findById(id);
        return foodPorId.map(mapper::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

    // Alterar
    public  FoodItemDTO alterarPorId(Long id, FoodItemDTO foodItemDTO) {
        Optional<FoodItem> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        FoodItem foodAtualizada = mapper.toEntity(foodItemDTO);
        foodAtualizada.setId(id);
        FoodItem salvo = repository.save(foodAtualizada);
        return mapper.toDTO(salvo);
    }

    // Deletar
    public void deletarFoodPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        repository.deleteById(id);
    }

}
