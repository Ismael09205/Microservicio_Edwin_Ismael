package com.Producto.API_Producto.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table (name="Productos")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class Productos {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idProducto;
    @NotBlank (message = "El nombre del producto no puede estar vacio")
    @Size(min=3,max=100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
    @Column(name="nombre_producto", nullable = false)
    private String nombreProducto;
    @NotBlank(message = "El precio del producto es obligatorio")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    @Column(length = 255)
    private String descripcion;
    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value="0.01",message = "El precio del producto debe ser mayor a 0")
    @Column(nullable=false)
    private double precio;
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;
    @NotNull(message = "El estado del producto es obligatorio")
    @Column(nullable = false)
    private boolean estadoProducto; //Disponible=true o no disponible=false

}

