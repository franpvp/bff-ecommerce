package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarInventarioRequest;
import com.example.bffazure.dto.CrearInventarioRequest;
import com.example.bffazure.dto.InventarioDTO;
import com.example.bffazure.feign.InventarioFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BffInventarioControllerTest {

    @Mock
    private InventarioFeignClient inventarioFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffInventarioController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarInventarioTest() {

        when(inventarioFeignClient.listar()).thenReturn(List.of(InventarioDTO.builder().build()));

        ResponseEntity<?> response = controller.listar(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(inventarioFeignClient).listar();
    }

    @Test
    void crearInventarioTest() {
        CrearInventarioRequest request = new CrearInventarioRequest();

        when(inventarioFeignClient.crear(request)).thenReturn(InventarioDTO.builder().build());

        ResponseEntity<?> response = controller.crear(request, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(inventarioFeignClient).crear(request);
    }

    @Test
    void obtenerInventarioPorProductoTest() {
        Long idProducto = 10L;
        Object inventarioMock = Map.of(
                "idInventario", 33L,
                "idProducto", idProducto,
                "cantidad", 12
        );

        when(inventarioFeignClient.obtenerPorProducto(idProducto)).thenReturn(InventarioDTO.builder().build());

        ResponseEntity<?> response = controller.obtenerPorProducto(idProducto, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(inventarioFeignClient).obtenerPorProducto(idProducto);
    }

    @Test
    void actualizarInventarioTest() {
        Long idProducto = 15L;
        ActualizarInventarioRequest request = new ActualizarInventarioRequest();


        when(inventarioFeignClient.actualizarCantidad(idProducto, request))
                .thenReturn(InventarioDTO.builder().build());

        ResponseEntity<?> response =
                controller.actualizar(idProducto, request, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(inventarioFeignClient).actualizarCantidad(idProducto, request);
    }

    @Test
    void eliminarInventarioTest() {
        Long idInventario = 99L;
        doNothing().when(inventarioFeignClient).eliminar(idInventario);

        ResponseEntity<?> response = controller.eliminar(idInventario, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(inventarioFeignClient).eliminar(idInventario);
    }
}