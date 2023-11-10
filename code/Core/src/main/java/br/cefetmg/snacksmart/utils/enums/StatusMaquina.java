package br.cefetmg.snacksmart.utils.enums;

public enum StatusMaquina {
    ALUGADA("ALUGADA"),
    EM_MANUTENCAO("EM_MANUTENCAO"),
    AGUARDANDO_MANUTENCAO("AGUARDANDO_MANUTENCAO"),
    DISPONIVEL("DISPONIVEL"),
    REMOVIDA("REMOVIDA");

    private String tipo;

    StatusMaquina(String tipo) {
        this.tipo = tipo;
    }
    
    public int toInt(StatusMaquina status) {
        return switch (status) {
            case ALUGADA -> 0;
            case EM_MANUTENCAO -> 1;
            case AGUARDANDO_MANUTENCAO -> 2;
            case DISPONIVEL -> 3;
            case REMOVIDA -> 4;
            default -> 0;
        };
    }
    
    public static StatusMaquina fromString(String statusStr) {
        statusStr = statusStr.replace(" ", "_"); 
        for (StatusMaquina status : StatusMaquina.values()) {
            if (status.toString().equalsIgnoreCase(statusStr)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status não reconhecido: " + statusStr);
    }
}
