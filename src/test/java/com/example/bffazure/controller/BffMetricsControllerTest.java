package com.example.bffazure.controller;

import com.example.bffazure.dto.OrdenEstadoMetricDto;
import com.example.bffazure.feign.OrdenesFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BffMetricsControllerTest {

    @Mock
    private OrdenesFeignClient ordenesFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffMetricsController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private OrdenEstadoMetricDto buildMetric(String estado, Long cantidad) {
        return OrdenEstadoMetricDto.builder()
                .estado(estado)
                .cantidad(cantidad)
                .build();
    }

    @Test
    void obtenerMetricasOrdenesHoyTest() {
        List<OrdenEstadoMetricDto> mockResponse = List.of(
                buildMetric("PENDIENTE", 5L),
                buildMetric("COMPLETADA", 12L)
        );

        when(ordenesFeignClient.obtenerMetricasOrdenesHoy())
                .thenReturn(mockResponse);

        ResponseEntity<List<OrdenEstadoMetricDto>> response =
                controller.obtenerMetricasOrdenesHoy(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getEstado()).isEqualTo("PENDIENTE");
        assertThat(response.getBody().get(1).getCantidad()).isEqualTo(12L);

        verify(ordenesFeignClient).obtenerMetricasOrdenesHoy();
    }

    @Test
    void obtenerCorrectasHoyTest() {
        Long mockValue = 15L;

        when(ordenesFeignClient.obtenerCorrectasHoy())
                .thenReturn(mockValue);

        ResponseEntity<Long> response =
                controller.obtenerCorrectasHoy(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockValue);

        verify(ordenesFeignClient).obtenerCorrectasHoy();
    }

    @Test
    void obtenerUsuariosActivosTest() {
        Long mockValue = 42L;

        when(ordenesFeignClient.obtenerUsuariosActivos())
                .thenReturn(mockValue);

        ResponseEntity<Long> response =
                controller.obtenerUsuariosActivos(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockValue);

        verify(ordenesFeignClient).obtenerUsuariosActivos();
    }
}