package br.cefetmg.snacksmart.utils.enums;

public enum StatusMaquina {
    DISPONIVEL,
    EM_FUNCIONAMENTO,
    EM_MANUTENCAO,
    AGUARDANDO_MANUTENCAO,
    REMOVIDO;
    
    public int toInt(StatusMaquina status) {
        return switch (status) {
            case DISPONIVEL -> 0;
            case EM_FUNCIONAMENTO -> 1;
            case EM_MANUTENCAO -> 2;
            case AGUARDANDO_MANUTENCAO -> 3;
            case REMOVIDO -> 4;
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
