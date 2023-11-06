package br.cefetmg.snacksmart.utils.enums;

public enum TiposOrdenacaContrato {
    MAIOR_ID("maior_id"),
    MENOR_ID("menor_id"),
    MAIOR_VALOR("maior_valor"),
    MENOR_VALOR("menor_valor"),
    FIM_MAIS_PROXIMA("fim_mais_próximo"),
    FIM_MAIS_DISTANTE("fim_mais_distante"),
    INICIO_MAIS_RECENTE("inicio_mais_recente"),
    INICIO_MAIS_DISTANTE("inicio_mais_distante");

    private final String tipo;

    TiposOrdenacaContrato(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo.replace('_', ' ');
    }

    public String toSql() {
        return switch (tipo) {
            case "maior_id" -> "ORDER BY pk DESC";
            case "menor_id" -> "ORDER BY pk ASC";
            case "maior_valor" -> "ORDER BY valor DESC";
            case "menor_valor" -> "ORDER BY valor ASC";
            case "fim_mais_próximo" -> "ORDER BY data_fim ASC";
            case "fim_mais_distante" -> "ORDER BY data_fim DESC";
            case "inicio_mais_próximo" -> "ORDER BY data_inicio DESC";
            case "inicio_mais_distante" -> "ORDER BY data_inicio ASC";
            default -> "";
        };
    }
}
