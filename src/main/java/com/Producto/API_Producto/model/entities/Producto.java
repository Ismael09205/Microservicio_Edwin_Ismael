package com.Producto.API_Producto.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name="productos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idProducto;

    @NotBlank(message = "IMPORTANTE: El codigo unico no debe de estar vacío")
    @Column(name = "codigo_unico", nullable = false, unique = true, updatable = false)
    private String codigoUnico;

    @NotBlank (message = "El nombre del producto no puede estar vacío")
    @Size(min=3,max=100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
    @Column(name="nombre_producto", nullable = false, unique = true)
    private String nombreProducto;

    @NotBlank(message = "La descripción del producto es obligatorio")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value="0.01",message = "El precio del producto debe ser mayor a 0")
    @Column(name = "precio", nullable=false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull(message = "El estado del producto es obligatorio")
    @Column(name = "estado_producto", nullable = false)
    private Boolean estadoProducto; //Disponible=true o no disponible=false

    @Size(max = 500, message = "La URL de la imagen es demasiado larga")
    @Column(name = "imagen_url",length = 500)
    private String imagenURL;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "fecha_registro", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaRegistro;

}

