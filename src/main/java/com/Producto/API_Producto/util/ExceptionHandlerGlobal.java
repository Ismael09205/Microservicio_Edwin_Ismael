package com.Producto.API_Producto.util;

import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerGlobal {
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<?> handleProductNotFound(ProductoNoEncontradoException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
