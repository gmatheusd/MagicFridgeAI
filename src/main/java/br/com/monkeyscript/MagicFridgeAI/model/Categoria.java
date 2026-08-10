package br.com.monkeyscript.MagicFridgeAI.model;

public enum Categoria {
    LATICINIOS("Laticínios"),
    CARNES("Carnes"),
    FRUTAS("Frutas"),
    VERDURAS("Verduras e Legumes"),
    GRAOS("Grãos e Cereais"),
    BEBIDAS("Bebidas"),
    CONGELADOS("Congelados"),
    ENLATADOS("Enlatados"),
    TEMPEROS("Temperos e Condimentos"),
    OUTROS("Outros");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
