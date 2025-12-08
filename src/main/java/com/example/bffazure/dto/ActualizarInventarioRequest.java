package com.example.bffazure.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarInventarioRequest {
    private Integer cantidad;
}
