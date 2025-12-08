package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private Integer precio;
    private Long idCategoria;
    private String imagenUrl;
}
