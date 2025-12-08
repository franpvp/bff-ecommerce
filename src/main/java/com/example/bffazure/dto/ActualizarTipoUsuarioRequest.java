package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActualizarTipoUsuarioRequest {

    private String nombreTipo;
    private String descripcion;
}
