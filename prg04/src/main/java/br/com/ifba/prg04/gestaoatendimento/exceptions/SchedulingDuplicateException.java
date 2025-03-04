package br.com.ifba.prg04.gestaoatendimento.exceptions;

public class SchedulingDuplicateException extends RuntimeException{
    public SchedulingDuplicateException(String message){
       super(message);
    }
   
}
