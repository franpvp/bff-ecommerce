package com.example.bffazure.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrdenEstadoDto {

    private Long idOrden;
    private String estadoOrden;
}
