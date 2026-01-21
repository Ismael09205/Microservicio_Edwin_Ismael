package com.Producto.API_Producto.model.interfaces;

import com.Producto.API_Producto.model.entities.Productos;

import java.util.List;

public interface IProductService {
    //Obtener Todos los Productos
    public List<Productos> getProducts();
    //Obtener los productos por ID
    public Productos findProducts(Long id);
    //Guardar Productos
    public void  saveProducts(Productos productos);
    //Eliminar Productos
    public void deleteProducts(Long id);
    //Actualizar Productos
    public Productos updateProducts(Long id,Productos productosNuevos);
}
