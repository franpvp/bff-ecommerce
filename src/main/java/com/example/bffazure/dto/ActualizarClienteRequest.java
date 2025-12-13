package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActualizarClienteRequest {

    private String telefono;
    private String direccion;
    private String ciudad;
}
