package com.Producto.API_Producto.service;

import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import com.Producto.API_Producto.model.interfaces.IProductService;
import com.Producto.API_Producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService implements IProductService {
    public final ProductoRepository productoRepository;

    @Autowired
    public ProductosService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProducts() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findProductsForID(Long id) {
        return productoRepository.findById(id).orElseThrow(() ->
                new ProductoNoEncontradoException(id));
    }

    @Override
    public Producto saveProducts(Producto productos) {
        return productoRepository.save(productos);
    }

    @Override
    public Producto updateProducts(Long id, Producto productosNuevos) {

        Producto existeProducto = findProductsForID(id);

        existeProducto.setNombreProducto(productosNuevos.getNombreProducto());
        existeProducto.setDescripcion(productosNuevos.getDescripcion());
        existeProducto.setPrecio(productosNuevos.getPrecio());
        existeProducto.setStock(productosNuevos.getStock());
        existeProducto.setEstadoProducto(productosNuevos.getEstadoProducto());
        existeProducto.setFechaRegistro(productosNuevos.getFechaRegistro());

        return productoRepository.save(existeProducto);
    }

    @Override
    public void deleteProducts(Long id) {
        Producto producto = findProductsForID(id);
        productoRepository.delete(producto);
    }
}
