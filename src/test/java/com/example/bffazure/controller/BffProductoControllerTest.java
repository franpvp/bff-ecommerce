package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarProductoRequest;
import com.example.bffazure.dto.CrearProductoRequest;
import com.example.bffazure.dto.ProductoDTO;
import com.example.bffazure.feign.ProductoFeignClient;
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

class BffProductoControllerTest {

    @Mock
    private ProductoFeignClient productoFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffProductoController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductosTest() {

        when(productoFeignClient.listar()).thenReturn(List.of(ProductoDTO.builder().build()));

        ResponseEntity<?> response = controller.listar();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(productoFeignClient).listar();
    }

    @Test
    void obtenerProductoTest() {
        Long id = 10L;

        when(productoFeignClient.obtener(id)).thenReturn(ProductoDTO.builder().build());

        ResponseEntity<?> response = controller.obtener(id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(productoFeignClient).obtener(id);
    }

    @Test
    void crearProductoTest() {
        CrearProductoRequest request = new CrearProductoRequest();

        when(productoFeignClient.crear(any(CrearProductoRequest.class)))
                .thenReturn(ProductoDTO.builder().build());

        ResponseEntity<?> response = controller.crear(request, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(productoFeignClient).crear(any(CrearProductoRequest.class));
    }

    @Test
    void actualizarProductoTest() {
        Long id = 5L;
        ActualizarProductoRequest request = new ActualizarProductoRequest();
        String productoActualizadoMock = "Producto Actualizado";

        when(productoFeignClient.actualizar(eq(id), any(ActualizarProductoRequest.class)))
                .thenReturn(ProductoDTO.builder().build());

        ResponseEntity<?> response =
                controller.actualizar(id, request, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(productoFeignClient)
                .actualizar(eq(id), any(ActualizarProductoRequest.class));
    }

    @Test
    void eliminarProductoTest() {
        Long id = 99L;

        doNothing().when(productoFeignClient).eliminar(id);

        ResponseEntity<?> response =
                controller.eliminar(id, authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(productoFeignClient).eliminar(id);
    }
}