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
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerGlobal {

    public DTOErrorResponse dtoError;

    //Producto No Encontrado(404)
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<DTOErrorResponse> handleProductNotFound(ProductoNoEncontradoException ex, HttpServletRequest request) {
        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Producto No Encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

         return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
     }


     //Producto Existente(409)
    @ExceptionHandler(ProductoExistenteException.class)
    public ResponseEntity<DTOErrorResponse> handleProductExist(ProductoExistenteException ex, HttpServletRequest request){
       DTOErrorResponse error =new DTOErrorResponse(
               HttpStatus.CONFLICT.value(),
               "Producto Duplicado",
               ex.getMessage(),
               request.getRequestURI(),
               LocalDateTime.now()
       );
       return ResponseEntity
               .status(HttpStatus.CONFLICT)
               .body(error);
    }

    //Tipo de dato Incorrecto(400)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DTOErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        String mensaje = "El parametro '"+ex.getName()+"' debe ser de tipo"+ex.getRequiredType().getSimpleName();

        DTOErrorResponse error = new DTOErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Parametro invalido",
                mensaje,
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    //Excepcion JSON mal formado(400)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DTOErrorResponse> handleJsonError(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        String mensaje = "El cuerpo del formato json esta mal formado o es invalido";
        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "JSON invalido",
                mensaje,
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    //Validaciones(400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DTOErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validacion",
                String.join("; ",errores),
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    //Excepcion general por si pasa errores inesperados(500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTOErrorResponse> handleGeneral(Exception ex, HttpServletRequest request){
        String mensaje = "Ocurrio un error inesperado: ";
        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno",
                mensaje+ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    //Manejo de errores para URL invalida
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DTOErrorResponse> handleNoHandlerFound(
            NoHandlerFoundException ex,
            HttpServletRequest request) {

        DTOErrorResponse error = new DTOErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "URL invalida",
                "La URL solicitada no existe, end point no encontrado",
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}

