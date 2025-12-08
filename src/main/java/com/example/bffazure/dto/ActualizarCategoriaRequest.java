package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActualizarCategoriaRequest {
    private String nombre;
    private String descripcion;
    private String nombreDirectorio;
}
