package br.com.monkeyscript.MagicFridgeAI.dto;

import br.com.monkeyscript.MagicFridgeAI.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private Long id;
    private String nome;
    private Categoria categoria;
    private Integer quantidade;
    private LocalDate validade;

}
