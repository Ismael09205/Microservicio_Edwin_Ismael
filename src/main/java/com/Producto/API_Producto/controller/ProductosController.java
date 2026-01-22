package com.Producto.API_Producto.controller;

import com.Producto.API_Producto.DTO.ProductCreateDTO;
import com.Producto.API_Producto.DTO.ProductUpdateDTO;
import com.Producto.API_Producto.model.entities.Producto;
import com.Producto.API_Producto.service.ProductosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api")
@Tag(name = "Productos", description = "Api para la gestion de productos.")
public class ProductosController {

    private ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }


    //Crear Producto
    @PostMapping("/productos")
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el sistema")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductCreateDTO dto) {
        Producto productoGuardado = productosService.saveProduct(dto);
        return new ResponseEntity<Producto>(productoGuardado, HttpStatus.CREATED);

    }


    //Listar Productos
    @GetMapping("/productos")
    @Operation(summary = "Listar productos", description = "Lista todos los productos que estan registrados")
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productosService.getProduct());
    }


    //Obtener Por Id
    @GetMapping("/productos/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Busca un producto especifico por ID")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
       return ResponseEntity.ok(productosService.findProduct(id));
    }


    //Actualizar Producto
    @PatchMapping("productos/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto registrado")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO productoActualizado) {
        return ResponseEntity.ok(productosService.updateProduct(id, productoActualizado));
    }

    //Eliminar Producto
    @DeleteMapping("productos/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productosService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
