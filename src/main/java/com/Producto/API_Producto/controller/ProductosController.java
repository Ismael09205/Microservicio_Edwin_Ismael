package com.Producto.API_Producto.controller;

import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class ProductosController {

    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService){
        this.productosService = productosService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listProducts(){
        return ResponseEntity.ok(productosService.getProducts());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductForID(@PathVariable Long id){
        return ResponseEntity.ok(productosService.findProductsForID(id));
    }

    @PostMapping("/productos/crear")
    public ResponseEntity<Producto> createProduct(@Valid @RequestBody Producto producto){
    Producto nuevoProducto=productosService.saveProducts(producto);
    return new ResponseEntity<Producto>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable Long id, @Valid @RequestBody Producto producto){
        Producto productUpdated = productosService.updateProducts(id, producto);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productosService.deleteProducts(id);
        return ResponseEntity.noContent().build();
    }

}


