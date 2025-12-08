package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
    private Long id;
    private Long idProducto;
    private Integer cantidad;
}
