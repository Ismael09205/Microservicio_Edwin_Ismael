package com.Producto.API_Producto.controller;

import com.Producto.API_Producto.DTO.ProductCreateDTO;
import com.Producto.API_Producto.DTO.ProductUpdateDTO;
import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api")
public class ProductosController {

    private ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }


    //Crear Producto
    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductCreateDTO dto) {
        Producto productoGuardado = productosService.saveProduct(dto);
        return new ResponseEntity<Producto>(productoGuardado, HttpStatus.CREATED);

    }


    //Listar Productos
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productosService.getProduct());
    }


    //Obtener Por Id
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
       return ResponseEntity.ok(productosService.findProduct(id));
    }


    //Actualizar Producto
    @PatchMapping("productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO productoActualizado) {
        return ResponseEntity.ok(productosService.updateProduct(id, productoActualizado));
    }

    //Eliminar Producto
    @DeleteMapping("productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productosService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
