package com.Producto.API_Producto.model.interfaces;

import com.Producto.API_Producto.DTO.ProductCreateDTO;
import com.Producto.API_Producto.DTO.ProductUpdateDTO;
import com.Producto.API_Producto.model.entities.Producto;

import java.util.List;

public interface IProductService {
    //Obtener Todos los Productos
    public List<Producto> getProduct();

    //Obtener los productos por ID
    public Producto findProduct(Long id);

    //Guardar Productos
    public Producto  saveProduct(ProductCreateDTO dto);

    //Eliminar Productos
    public void deleteProduct(Long id);

    //Actualizar Productos
    public Producto updateProduct(Long id, ProductUpdateDTO dto);
}
