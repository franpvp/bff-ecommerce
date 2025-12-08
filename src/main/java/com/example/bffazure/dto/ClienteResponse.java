package com.example.bffazure.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String ciudad;
    private LocalDateTime fechaRegistro;
}
