package br.cefetmg.snacksmart.utils.enums;

/**
 *
 * @author eloym
 */
public enum StatusContrato {
    VIGENTE("VIGENTE"),
    EXPIRADO("EXPIRADO"),
    INATIVO("INATIVO"),
    CANCELADO("CANCELADO"),
    CANCELAMENTO_SOLICITADO("CANCELAMENTO_SOLICITADO"),
    INVALIDO("INVALIDO");

    private String status;

    StatusContrato(String status) {
        this.status = status;
    }

    public static StatusContrato parse(String status) {
        switch (status) {
            case "VIGENTE": return VIGENTE;
            case "EXPIRADO": return EXPIRADO;
            case "INATIVO": return INATIVO;
            case "CANCELADO": return CANCELADO;
            case "CANCELAMENTO_SOLICITADO": return CANCELAMENTO_SOLICITADO;
            default: return INVALIDO;
        }
    }

    @Override
    public String toString() {
        return status;
    }
}
