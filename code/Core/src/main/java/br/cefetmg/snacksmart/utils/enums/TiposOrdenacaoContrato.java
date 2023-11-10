package br.cefetmg.snacksmart.utils.enums;

public enum TiposOrdenacaoContrato {
    MENOR_ID("MENOR_ID"),
    MAIOR_ID("MAIOR_ID"),
    MAIOR_VALOR("MAIOR_VALOR"),
    MENOR_VALOR("MENOR_VALOR"),
    FIM_MAIS_PROXIMO("FIM_MAIS_PROXIMO"),
    FIM_MAIS_DISTANTE("FIM_MAIS_DISTANTE"),
    INICIO_MAIS_RECENTE("INICIO_MAIS_RECENTE"),
    INICIO_MAIS_DISTANTE("INICIO_MAIS_DISTANTE");

    private final String tipo;

    TiposOrdenacaoContrato(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo.replace('_', ' ');
    }
    
    public String value() {
        return tipo;
    }

    public String toSql() {
        return switch (tipo) {
            case "MAIOR_ID" -> "ORDER BY pk DESC";
            case "MENOR_ID" -> "ORDER BY pk ASC";
            case "MAIOR_VALOR" -> "ORDER BY valor DESC";
            case "MENOR_VALOR" -> "ORDER BY valor ASC";
            case "FIM_MAIS_PROXIMO" -> "ORDER BY data_fim ASC";
            case "FIM_MAIS_DISTANTE" -> "ORDER BY data_fim DESC";
            case "INICIO_MAIS_RECENTE" -> "ORDER BY data_inicio DESC";
            case "INICIO_MAIS_DISTANTE" -> "ORDER BY data_inicio ASC";
            default -> "";
        };
    }
}
