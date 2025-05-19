package domus.challenge.adapter.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "The threshold parameter must be an integer");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Manejador específico para valores alfabéticos o no numéricos
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleInvalidThresholdFormat() {
        Map<String, String> error = new HashMap<>();
        error.put("error", "The threshold parameter must be an integer");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}