package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearTipoUsuarioRequest {

    private String nombreTipo;
    private String descripcion;
}
