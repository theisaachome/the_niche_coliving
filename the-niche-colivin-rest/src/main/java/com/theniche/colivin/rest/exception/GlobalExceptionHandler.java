package com.theniche.colivin.rest.exception;
import com.theniche.colivin.domain.exception.ConstraintMessageResolver;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ConstraintMessageResolver resolver;

    public GlobalExceptionHandler(ConstraintMessageResolver resolver) {
        this.resolver = resolver;
    }


    // DataIntegrityViolationException
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        var userMessage = resolver.resolveMessage(ex.getMostSpecificCause().getMessage());

        var errorDetails = new ErrorDetails(LocalDateTime.now(),userMessage,null);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleHibernateConstraintViolation(
            ConstraintViolationException ex,
            ConstraintMessageResolver resolver) {

        String userMessage = resolver.resolveMessage(ex.getSQLException().getMessage());

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                userMessage,
                null
        );

        return ResponseEntity.badRequest().body(errorDetails);
    }

    // handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Application Exception handler
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException exception,WebRequest request){
        var errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,WebRequest request){
        var errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var field = ((FieldError)error).getField();
            var message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)  // for debugging purpose
    public ResponseEntity<String> debug(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.getClass().getName());
    }

}
