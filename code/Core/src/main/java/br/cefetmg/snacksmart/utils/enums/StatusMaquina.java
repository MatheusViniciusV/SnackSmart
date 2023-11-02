package br.cefetmg.snacksmart.utils.enums;

public enum StatusMaquina {
    ALUGADA,
    EM_MANUTENCAO,
    AGUARDANDO_MANUTENCAO,
    DISPONIVEL;
    
    public int toInt(StatusMaquina status) {
        return switch (status) {
            case ALUGADA -> 0;
            case EM_MANUTENCAO -> 1;
            case AGUARDANDO_MANUTENCAO -> 2;
            case DISPONIVEL -> 3;
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
