package com.Producto.API_Producto.repository;

import com.Producto.API_Producto.model.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsByNombreProducto(String nombre);

    boolean existsByNombreProductoAndIdProductoNot(String nombre, Long id);

}
