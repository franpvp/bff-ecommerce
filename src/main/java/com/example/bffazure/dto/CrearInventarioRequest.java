package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CrearInventarioRequest {
    private Long idProducto;
    private Integer cantidad;
}
