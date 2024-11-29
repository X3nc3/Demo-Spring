package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.exception.Controle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Controle.class)
    protected ResponseEntity<String> traiterErreurs(Controle exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
