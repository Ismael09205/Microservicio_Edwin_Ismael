package com.Producto.API_Producto.model.interfaces;

import com.Producto.API_Producto.model.entities.Producto;

import java.util.List;

public interface IProductService {

    //Obtener Todos los Productos
    public List<Producto> getProducts();

    //Obtener los productos por ID
    public Producto findProductsForID(Long id);

    //Guardar Productos
    public Producto saveProducts(Producto productos);

    //Actualizar Productos
    public Producto updateProducts(Long id, Producto productosNuevos);

    //Eliminar Productos
    public void deleteProducts(Long id);


}
