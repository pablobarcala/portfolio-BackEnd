package com.portfolio.pruebajwt.exceptions;

import com.portfolio.pruebajwt.dto.Mensaje;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerException {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mensaje> handleExecption(Exception e){
        return ResponseEntity.internalServerError().body(new Mensaje(e.getMessage()));
    }
    
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Mensaje> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getStatus())
                .body(new Mensaje(e.getMessage()));
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Mensaje> handleBadCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new Mensaje(e.getMessage()));
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Mensaje> handleAccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(403))
                .body(new Mensaje(e.getMessage()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Mensaje> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> mensajes = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> mensajes.add(err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatusCode.valueOf(400))
                .body(new Mensaje(mensajes.stream().collect(Collectors.joining(","))));
    }
}
