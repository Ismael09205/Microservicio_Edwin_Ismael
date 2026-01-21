package com.Producto.API_Producto.service;

import com.Producto.API_Producto.model.entities.Productos;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import com.Producto.API_Producto.model.interfaces.IProductService;
import com.Producto.API_Producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService implements IProductService {
    public final ProductoRepository ProductoRepository;

    public ProductosService(ProductoRepository productoRepository) {
        ProductoRepository = productoRepository;
    }

    @Override
    public List<Productos> getProducts() {
        List<Productos> listProducts=ProductoRepository.findAll();
        return listProducts;
    }

    @Override
    public Productos findProducts(Long id) {
        return ProductoRepository.findById(id).orElseThrow(()-> new ProductoNoEncontradoException(id));
    }

    @Override
    public void saveProducts(Productos productos) {
        ProductoRepository.save(productos);
    }

    @Override
    public void deleteProducts(Long id) {
        ProductoRepository.deleteById(id);
    }

    @Override
    public Productos updateProducts(Long id,Productos productosNuevos) {
        Productos productoExistente=ProductoRepository.findById(id).orElse(null);
        if(productoExistente==null){
            return null;
        }
        if(productosNuevos.getNombreProducto()!=null){
            productoExistente.setNombreProducto(productosNuevos.getNombreProducto());
        }
        if(productosNuevos.getDescripcion()!=null){
            productoExistente.setDescripcion(productosNuevos.getDescripcion());
    }
        if(productosNuevos.getPrecio()!=null){
            productoExistente.setPrecio(productosNuevos.getPrecio());
        }
        if(productosNuevos.getStock()!=null){
            productoExistente.setPrecio(productosNuevos.getPrecio());
        }
        if(productosNuevos.getEstadoProducto()!=null){
            productoExistente.setEstadoProducto(productosNuevos.getEstadoProducto());
        }
        ProductoRepository.save(productoExistente);
        return productoExistente;
    }
}
