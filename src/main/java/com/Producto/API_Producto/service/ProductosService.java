package com.Producto.API_Producto.service;

import com.Producto.API_Producto.DTO.ProductCreateDTO;
import com.Producto.API_Producto.DTO.ProductUpdateDTO;
import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.model.exceptions.ProductoExistenteException;
import com.Producto.API_Producto.model.exceptions.ProductoNoEncontradoException;
import com.Producto.API_Producto.model.interfaces.IProductService;
import com.Producto.API_Producto.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProductosService implements IProductService {

    public final ProductoRepository productoRepository;
    private final Random random = new Random();

    @Autowired
    public ProductosService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProduct() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findProduct(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(()-> new ProductoNoEncontradoException(id));
    }

    @Override
    public Producto saveProduct(ProductCreateDTO dto) {
        Producto producto = new Producto();

        String codigoUnico;
        do {
            codigoUnico = generarCodigoUnico();
        }while(productoRepository.existsByCodigoUnico(codigoUnico));

        if (productoRepository.existsByNombreProducto(dto.getNombreProducto())) {
            throw new ProductoExistenteException("El producto con nombre '" + dto.getNombreProducto() + "' ya existe.");
        }

        producto.setCodigoUnico(codigoUnico);
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setEstadoProducto(dto.getEstadoProducto());
        producto.setImagenURL(dto.getImagenURL());

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

        if (dto.getNombreProducto() != null){
            productoExistente.setNombreProducto(dto.getNombreProducto());
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
        if (dto.getImagenURL()!=null){
            productoExistente.setImagenURL(dto.getImagenURL());
        }
        return productoRepository.save(productoExistente);
    }

    private String generarCodigoUnico(){
        long numero = (long) (random.nextDouble()*1_000_000_000_000L);
        return "PROD-"+String.format("%012d", numero);
    }
}
