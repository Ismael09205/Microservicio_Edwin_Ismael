package com.Producto.API_Producto.model.exceptions;

public class ProductoNoEncontradoException extends RuntimeException{
    public ProductoNoEncontradoException(Long id){
        super("Producto con id" + id + " no encontrado.");
    }
}
