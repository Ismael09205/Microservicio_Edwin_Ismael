package com.Producto.API_Producto.util;

import com.Producto.API_Producto.DTO.DTOErrorResponse;
import com.Producto.API_Producto.model.exceptions.ProductoExistenteException;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerGlobal {
    public DTOErrorResponse dtoError;
    //Producto No Encontrado
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<DTOErrorResponse> handleProductNotFound(ProductoNoEncontradoException ex, HttpServletRequest request) {
        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Producto No Encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

         return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
     }
     //Producto Existente
    @ExceptionHandler(ProductoExistenteException.class)
    public ResponseEntity<DTOErrorResponse> handleProductExist(ProductoExistenteException ex, HttpServletRequest request){
       DTOErrorResponse error =new DTOErrorResponse(
               HttpStatus.CONFLICT.value(),
               "Producto Duplicado",
               ex.getMessage(),
               request.getRequestURI()
       );
       return ResponseEntity
               .status(HttpStatus.CONFLICT)
               .body(error);
    }

    //Tipo de dato Incorrecto

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DTOErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest()
                .body(new DTOErrorResponse(
                        400,
                        "Parametro_Invalido",
                        "Envie un parametro valido de tipo " + ex.getRequiredType().getSimpleName(),
                        request.getRequestURI()
                ));
    }
    //Excepcion JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DTOErrorResponse> handleJsonError(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest()
                .body(new DTOErrorResponse(
                        400,
                        "JSON_Invalido",
                        "El cuerpo de la solicitud es inválido o está mal formado",
                        request.getRequestURI()
                ));
    }
    //Validaciones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DTOErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Datos inválidos");

        return ResponseEntity.badRequest()
                .body(new DTOErrorResponse(
                        400,
                        "VALIDACION_ERROR",
                        mensaje,
                        request.getRequestURI()
                ));
    }
}

