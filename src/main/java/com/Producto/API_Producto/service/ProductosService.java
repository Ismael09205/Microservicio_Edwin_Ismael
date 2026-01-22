package com.Producto.API_Producto.service;

import com.Producto.API_Producto.DTO.ProductCreateDTO;
import com.Producto.API_Producto.DTO.ProductUpdateDTO;
import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.model.exceptions.ProductoExistenteException;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import com.Producto.API_Producto.model.interfaces.IProductService;
import com.Producto.API_Producto.repository.ProductoRepository;
import com.Producto.API_Producto.util.NormalizerText;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductosService implements IProductService {

    public final ProductoRepository productoRepository;

    @Autowired
    public ProductosService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProduct() {
        List<Producto> listProducts= productoRepository.findAll();
        return listProducts;
    }

    @Override
    public Producto findProduct(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(()-> new ProductoNoEncontradoException(id));
    }

    @Override
    public Producto saveProduct(ProductCreateDTO dto) {
        Producto producto = new Producto();
        String nombreNormalizado = NormalizerText.normalizar(dto.getNombreProducto());

        if (productoRepository.existsByNombreProducto(nombreNormalizado)) {
            throw new ProductoExistenteException(dto.getNombreProducto());
        }

        producto.setNombreProducto(nombreNormalizado);
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setEstadoProducto(dto.getEstadoProducto());

        return productoRepository.save(producto);

    }

    @Override
    public void deleteProduct(Long id) {
        if(!productoRepository.existsById(id)){
            throw new ProductoNoEncontradoException(id);
        }
        productoRepository.deleteById(id);
    }

    public Producto updateProduct(Long id, ProductUpdateDTO dto) {

        Producto productoExistente= productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));

        if(dto.getNombreProducto() != null){
            String nombreNormalizado = NormalizerText.normalizar(dto.getNombreProducto());

            //Se verifica que no exista otro producto con el mismo nombre para evitar duplicados en la bases de datos
            boolean productExist = productoRepository.existsByNombreProductoAndIdProductoNot(nombreNormalizado, id)
                    && !productoExistente.getNombreProducto().equalsIgnoreCase(nombreNormalizado);
            if(productExist){
                throw new ProductoExistenteException(dto.getNombreProducto());
            }
            productoExistente.setNombreProducto(nombreNormalizado);
        }

        if(dto.getDescripcion()!=null){
            productoExistente.setDescripcion(dto.getDescripcion());
        }

        if(dto.getPrecio()!=null){
            productoExistente.setPrecio(dto.getPrecio());
        }
        if(dto.getStock()!=null){
            productoExistente.setStock(dto.getStock());
        }
        if(dto.getEstadoProducto()!=null){
            productoExistente.setEstadoProducto(dto.getEstadoProducto());
        }
        return productoRepository.save(productoExistente);
    }
}
