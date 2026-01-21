package com.Producto.API_Producto.model.exceptions;

public class ProductoExistenteException extends RuntimeException{
    public ProductoExistenteException (String nombre){
        super("Producto con nombre " + nombre + " ya existe.");
    }
}
