package br.cefetmg.snacksmart.utils.enums;

public enum TipoMaquina {
    NAO_REFRIGERADA,
    REFRIGERADA;
    
    public int toInt(TipoMaquina tipo) {
        return switch (tipo) {
            case NAO_REFRIGERADA -> 0;
            case REFRIGERADA -> 1;
            default -> 0;
        };
    }
    
    public static TipoMaquina fromString(String tipoStr) {
        tipoStr = tipoStr.replace(" ", "_"); 
        for (TipoMaquina tipo : TipoMaquina.values()) {
            if (tipo.toString().equalsIgnoreCase(tipoStr)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Status não reconhecido: " + tipoStr);
    }
}
