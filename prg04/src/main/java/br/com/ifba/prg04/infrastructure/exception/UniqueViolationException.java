package br.com.ifba.prg04.infrastructure.exception;

public class UniqueViolationException extends RuntimeException{
    public UniqueViolationException(String message) {
        super(message);
    }
}
