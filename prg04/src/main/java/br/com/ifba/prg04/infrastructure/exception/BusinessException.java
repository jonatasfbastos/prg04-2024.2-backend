package br.com.ifba.prg04.infrastructure.exception;

import java.io.Serial;

// Classe de exceção personalizada que estende RuntimeException
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Identificador único para a serialização

    // Construtor padrão
    public BusinessException() {
        super();
    }

    // Construtor que recebe uma mensagem de erro
    public BusinessException(final String message) {
        super(message);
    }

    // Construtor que recebe uma causa (outra exceção encadeada)
    public BusinessException(final Throwable cause) {
        super(cause);
    }

    // Construtor que recebe uma mensagem e uma causa
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}