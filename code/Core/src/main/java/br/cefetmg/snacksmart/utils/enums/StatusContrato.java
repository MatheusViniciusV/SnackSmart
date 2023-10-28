package br.cefetmg.snacksmart.utils.enums;

/**
 *
 * @author eloym
 */
public enum StatusContrato {
    INVALIDO,
    ATIVO,
    CANCELAMENTO_SOLICITADO,
    CANCELADO,
    EXCLUIDO;
    
    public int toInt(StatusContrato status) {
        return switch (status) {
            case INVALIDO -> 0;
            case ATIVO -> 1;
            case CANCELAMENTO_SOLICITADO -> 2;
            case CANCELADO -> 3;
            case EXCLUIDO -> 4;
            default -> 0;
        };
    }
}
