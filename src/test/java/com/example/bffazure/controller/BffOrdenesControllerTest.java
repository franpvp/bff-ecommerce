package com.example.bffazure.controller;

import com.example.bffazure.dto.*;
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

class BffOrdenesControllerTest {

    @Mock
    private OrdenesFeignClient ordenesFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffOrdenesController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private DetalleOrdenDto buildDetalle(Long idDetalle, Long idProducto, int cantidad, int precioUnitario) {

        ProductoOrdenDto producto = ProductoOrdenDto.builder()
                .idProducto(idProducto)
                .nombre("Producto Test")
                .precio(precioUnitario)
                .build();

        return DetalleOrdenDto.builder()
                .idDetalleOrden(idDetalle)
                .idProducto(idProducto)
                .cantidad(cantidad)
                .producto(producto)
                .build();
    }

    private PagoDto buildPago(Long idOrden, int monto) {
        return PagoDto.builder()
                .idOrden(idOrden)
                .idMetodoPago(1L)
                .monto(monto)
                .reprocesado(false)
                .build();
    }

    private DespachoDto buildDespacho() {
        return DespachoDto.builder()
                .direccion("Calle 123")
                .ciudadComuna("Santiago")
                .build();
    }

    private OrdenDto buildOrdenDto(Long idOrden) {

        List<DetalleOrdenDto> detalles = List.of(
                buildDetalle(1L, 10L, 2, 5000), // subtotal = 10000
                buildDetalle(2L, 11L, 1, 7000)  // subtotal = 7000
        );

        return OrdenDto.builder()
                .idOrden(idOrden)
                .idCliente(200L)
                .listaDetalle(detalles)
                .pagoDto(buildPago(idOrden, 17000))
                .despachoDto(buildDespacho())
                .build();
    }

    private OrdenEstadoDto buildOrdenEstado(Long id) {
        return OrdenEstadoDto.builder()
                .idOrden(id)
                .estadoOrden("COMPLETADA")
                .build();
    }

    @Test
    void crearOrdenTest() {
        OrdenDto request = buildOrdenDto(null);
        OrdenDto responseMock = buildOrdenDto(1L);

        when(ordenesFeignClient.crear(request)).thenReturn(responseMock);

        ResponseEntity<OrdenDto> response =
                controller.crearOrden(authentication, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getIdOrden()).isEqualTo(1L);

        verify(ordenesFeignClient).crear(request);
    }

    @Test
    void obtenerUltimaOrdenTest() {
        Long idCliente = 700L;
        OrdenEstadoDto mock = buildOrdenEstado(30L);

        when(ordenesFeignClient.obtenerUltimaOrden(idCliente))
                .thenReturn(mock);

        ResponseEntity<?> response =
                controller.obtenerUltimaOrden(authentication, idCliente);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(((OrdenEstadoDto) response.getBody()).getIdOrden()).isEqualTo(30L);

        verify(ordenesFeignClient).obtenerUltimaOrden(idCliente);
    }

    @Test
    void buscarOrdenPorIdTest() {
        Long idOrden = 15L;
        OrdenDto mock = buildOrdenDto(idOrden);

        when(ordenesFeignClient.buscarOrdenPorId(idOrden))
                .thenReturn(mock);

        ResponseEntity<OrdenDto> response =
                controller.buscarOrdenPorId(idOrden);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getIdOrden()).isEqualTo(idOrden);

        verify(ordenesFeignClient).buscarOrdenPorId(idOrden);
    }

    @Test
    void obtenerHistorialClienteTest() {
        Long idCliente = 500L;

        List<OrdenDto> mockHistorial = List.of(
                buildOrdenDto(1L),
                buildOrdenDto(2L)
        );

        when(ordenesFeignClient.obtenerHistorialCliente(idCliente))
                .thenReturn(mockHistorial);

        ResponseEntity<List<OrdenDto>> response =
                controller.obtenerHistorialCliente(authentication, idCliente);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);

        verify(ordenesFeignClient).obtenerHistorialCliente(idCliente);
    }
}