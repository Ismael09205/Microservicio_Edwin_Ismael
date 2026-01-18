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

@RestController
@RequestMapping ("/api/")
public class ProductosController {
    @Autowired
    private ProductosService productosService;
    @PostMapping("productos")
    public ResponseEntity<Productos> crearProducto(@Valid @RequestBody Productos producto){
    Productos productoGuardado=productosService.createProducto(producto);
    return new ResponseEntity<Productos>(productoGuardado, HttpStatus.CREATED);

    }
    @GetMapping ("mostrarProductos")
    public List<Productos>listarTodos(){
        return productosService.listarProductos();
    };

}


