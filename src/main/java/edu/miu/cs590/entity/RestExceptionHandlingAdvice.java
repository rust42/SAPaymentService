package edu.miu.cs590.entity;

import edu.miu.cs590.dto.ErrorResponse;
import edu.miu.cs590.exceptions.PaymentMethodNotFoundException;
import edu.miu.cs590.exceptions.UnAuthorizedRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Component
public class RestExceptionHandlingAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentMethodNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> paymentNotFound(PaymentMethodNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UnAuthorizedRequestException.class)
    public ResponseEntity<ErrorResponse> unauthorizedRequestException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.UNAUTHORIZED,
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalServerException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
