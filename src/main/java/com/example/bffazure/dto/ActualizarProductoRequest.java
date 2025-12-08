package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActualizarProductoRequest {
    private String nombre;
    private String descripcion;
    private String marca;
    private Double precio;
    private String imagenUrl;
    private Long idCategoria;
}

