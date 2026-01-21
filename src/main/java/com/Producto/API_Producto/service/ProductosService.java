package com.Producto.API_Producto.service;

import com.Producto.API_Producto.model.entities.Productos;
import com.Producto.API_Producto.model.exceptions.ProductoExistenteException;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import com.Producto.API_Producto.model.interfaces.IProductService;
import com.Producto.API_Producto.repository.ProductoRepository;
import com.Producto.API_Producto.util.NormalizerText;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Productos saveProducts(Productos productos) {
        String nombreNormalizado= NormalizerText.normalizar(productos.getNombreProducto());
        if(ProductoRepository.existsByNombreProducto(nombreNormalizado)){
            throw new ProductoExistenteException(productos.getNombreProducto());
        }
        return ProductoRepository.save(productos);
    }

    @Override
    public void deleteProducts(Long id) {
        if(!ProductoRepository.existsById(id)){
            throw new ProductoNoEncontradoException(id);
        }
        ProductoRepository.deleteById(id);
    }

    @Override
    public Productos updateProducts(Long id,Productos productosNuevos) {
        Productos productoExistente=ProductoRepository.findById(id).orElse(null);
        if(productoExistente==null){
            throw new ProductoNoEncontradoException(id);
        }
        if(productosNuevos.getNombreProducto()!=null){
            String normalizarNombre=NormalizerText.normalizar(productosNuevos.getNombreProducto());
            boolean productExists= ProductoRepository.existsByNombreProductoAndIdProducto(normalizarNombre,id);
            if(productExists){
                throw new ProductoExistenteException(productosNuevos.getNombreProducto());
            }
            productoExistente.setNombreProducto(normalizarNombre);
        }
        if(productosNuevos.getDescripcion()!=null){
            productoExistente.setDescripcion(productosNuevos.getDescripcion());
    }
        if(productosNuevos.getPrecio()!=null){
            productoExistente.setPrecio(productosNuevos.getPrecio());
        }
        if(productosNuevos.getStock()!=null){
            productoExistente.setStock(productosNuevos.getStock());
        }
        if(productosNuevos.getEstadoProducto()!=null){
            productoExistente.setEstadoProducto(productosNuevos.getEstadoProducto());
        }
        ProductoRepository.save(productoExistente);
        return productoExistente;
    }
}
