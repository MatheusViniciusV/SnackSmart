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

    @Override
    public String toString() {
        return status;
    }
}
