package br.com.monkeyscript.MagicFridgeAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "Food_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column (name = "categoria")
    private Categoria categoria;

    @Column (name = "quantidade")
    private Integer quantidade;

    @Column (name = "validade")
    private LocalDate validade;

}
