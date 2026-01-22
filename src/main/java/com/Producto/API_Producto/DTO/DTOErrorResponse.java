package com.Producto.API_Producto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime fecha;

}
