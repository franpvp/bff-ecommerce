package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CrearCategoriaRequest {
    private String nombre;
    private String descripcion;
    private String nombreDirectorio;
}
