package com.Producto.API_Producto.controller;

import com.Producto.API_Producto.model.entities.Productos;
import com.Producto.API_Producto.repository.ProductoRepository;
import com.Producto.API_Producto.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/productos/")
public class ProductosController {

    private ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    //Crear Producto
    @PostMapping("crear")
    public ResponseEntity<Productos> crearProducto(@Valid @RequestBody Productos producto) {
        Productos productoGuardado = productosService.saveProducts(producto);
        return new ResponseEntity<Productos>(productoGuardado, HttpStatus.CREATED);

    }
    //Listar Productos
    @GetMapping("mostrarProductos")
    public ResponseEntity<List<Productos>> listarTodos() {
        return ResponseEntity.ok(productosService.getProducts());
    }
    //Obtener Por Id
    @GetMapping("productosObtener/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Long id) {
       return ResponseEntity.ok(productosService.findProducts(id));
    }
    //Actualizar Producto
    @PatchMapping("productosActualizar/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Productos productoActualizado) {

        return ResponseEntity.ok(productosService.updateProducts(id, productoActualizado));

    }
    //Eliminar Producto
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productosService.deleteProducts(id);
        return ResponseEntity.noContent().build();
    }

}
