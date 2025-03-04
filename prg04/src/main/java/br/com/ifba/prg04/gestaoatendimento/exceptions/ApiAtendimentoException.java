package br.com.ifba.prg04.gestaoatendimento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiAtendimentoException {
    // api de excecoes com excecoes personalizadas
     @ExceptionHandler(UniqueCodeViolationException.class)
    public ResponseEntity<String> handleUniqueCodeViolationException(UniqueCodeViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SchedulingDuplicateException.class)
    public ResponseEntity<String> handleSchedulingDuplicateException(SchedulingDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(SchedulingNotFoundException.class)
    public ResponseEntity<String> handleSchedulingNotFoundException(SchedulingDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    

    // Exceções gerais
    @ExceptionHandler(HandleGenericException.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
