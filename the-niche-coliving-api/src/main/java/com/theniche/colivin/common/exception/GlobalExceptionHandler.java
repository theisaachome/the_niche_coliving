package com.theniche.colivin.common.exception;

import com.theniche.colivin.common.payload.ErrorDetailsResponse;
import org.hibernate.TypeMismatchException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var errorDetails = new ErrorDetailsResponse()
                .setTimestamp(LocalDateTime.now())
                .setMessage(ex.getMessage())
                .setDetails(request.getDescription(false))
                .setErrorCode( HttpStatus.NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

    }
    // 400 - Invalid ID format (UUID)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetailsResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String fieldName = ex.getName(); // houseId
        String invalidValue = String.valueOf(ex.getValue());

        var errorDetails = new ErrorDetailsResponse()
                .setTimestamp(LocalDateTime.now())
                .setMessage(
                        String.format("Invalid %s format: '%s'", fieldName, invalidValue)
                )
                .setDetails("Expected a valid UUID")
                .setErrorCode(HttpStatus.BAD_REQUEST.name());

        return ResponseEntity.badRequest().body(errorDetails);
    }

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception ex, WebRequest request) {

        var errorDetails = new ErrorDetailsResponse()
                .setTimestamp(LocalDateTime.now())
                .setMessage(ex.getMessage())
                .setDetails("Expected a valid UUID")
                .setErrorCode(HttpStatus.BAD_REQUEST.name());

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName =((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName,message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
