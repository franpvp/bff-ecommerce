package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuarioResponse {

    private Long id;
    private String nombreTipo;
    private String descripcion;
}
