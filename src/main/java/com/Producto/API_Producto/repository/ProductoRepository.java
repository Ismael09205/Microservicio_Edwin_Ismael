package com.Producto.API_Producto.repository;

import com.Producto.API_Producto.model.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Long> {

    boolean existsByNombreProducto(String nombre);

    boolean existsByNombreProductoAndIdProducto(String nombre, Long id);

}
