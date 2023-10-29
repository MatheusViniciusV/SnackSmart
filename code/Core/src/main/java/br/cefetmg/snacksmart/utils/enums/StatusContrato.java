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
    
    public int toStatus(int valor) {
        StatusContrato status;
        
        switch(valor) {
            case 0:
                status = StatusContrato.INVALIDO;
                break;
            case 1:
                status = StatusContrato.ATIVO;
                break;
            case 2:
                status = StatusContrato.CANCELAMENTO_SOLICITADO;
                break;
            case 3:
                status = StatusContrato.CANCELADO;
                break;
            case 4:
                status = StatusContrato.EXCLUIDO;
                break;
            default:
                status = StatusContrato.INVALIDO;
                break;
        }
        
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
