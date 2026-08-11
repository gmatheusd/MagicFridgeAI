package br.com.monkeyscript.MagicFridgeAI.mapper;

import br.com.monkeyscript.MagicFridgeAI.dto.FoodItemDTO;
import br.com.monkeyscript.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {

    // Tomei conhecimento que é uma convenção usar toEntity e toDTO ao invés de map
    public FoodItem toEntity(FoodItemDTO dto) {

        FoodItem model = new FoodItem();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setQuantidade(dto.getQuantidade());
        model.setCategoria(dto.getCategoria());
        model.setValidade(dto.getValidade());

        return model;
    }

    public FoodItemDTO toDTO(FoodItem model) {

        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setQuantidade(model.getQuantidade());
        dto.setCategoria(model.getCategoria());
        dto.setValidade(model.getValidade());

        return dto;
    }

}
