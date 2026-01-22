package com.Producto.API_Producto.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateDTO {

    @Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
    private String nombreProducto;

    @Size(min = 5, max = 255, message = "La descripcion debe tener de entre 5 a 255 caracteres")
    private String descripcion;

    @DecimalMin(value = "0.01", message = "El precio del producto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 2 decimales")
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Max(value = 999999, message = "El stock no puede exceder 999999 unidades")
    private Integer stock;

    private Boolean estadoProducto;

    @Size(max = 500, message = "La URL de la imagen es demasiado larga")
    @Column(name = "imagen_url",length = 500)
    private String imagenURL;
}
