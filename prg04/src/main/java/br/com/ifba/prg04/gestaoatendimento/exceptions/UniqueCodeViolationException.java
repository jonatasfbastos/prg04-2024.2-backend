package br.com.ifba.prg04.gestaoatendimento.exceptions;

public class UniqueCodeViolationException extends RuntimeException {
    public UniqueCodeViolationException(String message){
        super(message);
    }

}
