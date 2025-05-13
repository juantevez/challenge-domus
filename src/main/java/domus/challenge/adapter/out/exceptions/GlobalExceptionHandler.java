package domus.challenge.adapter.out.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleInvalidParamFormat() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "The threshold must be a integer number greater than 0.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidThresholdValue() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "The threshold must be a integer number greater than 0.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}