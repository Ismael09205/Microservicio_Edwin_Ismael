package com.Producto.API_Producto.service;

import com.Producto.API_Producto.model.entities.Productos;
import com.Producto.API_Producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {
    //Inyeccion de dependencias
    @Autowired
    private ProductoRepository productoRepository;

    //Crear un Nuevo Producto
    public Productos createProducto (Productos producto){
        return productoRepository.save(producto);
    }
    //Mostrar Todos los productos
    public List<Productos> listarProductos(){
        return productoRepository.findAll();
    }

}
