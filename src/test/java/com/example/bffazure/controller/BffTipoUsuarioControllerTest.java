package com.example.bffazure.controller;

import com.example.bffazure.dto.*;
import com.example.bffazure.feign.TipoUsuarioFeignClient;
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

class BffTipoUsuarioControllerTest {

    @Mock
    private TipoUsuarioFeignClient tipoUsuarioFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffTipoUsuarioController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private TipoUsuarioResponse buildTipoUsuario(Long id, String nombre) {
        return TipoUsuarioResponse.builder()
                .id(id)
                .nombreTipo(nombre)
                .build();
    }

    @Test
    void crearTipoUsuarioTest() {
        CrearTipoUsuarioRequest request = new CrearTipoUsuarioRequest();
        TipoUsuarioResponse mockResp = buildTipoUsuario(1L, "ADMIN");

        when(tipoUsuarioFeignClient.crear(any(CrearTipoUsuarioRequest.class)))
                .thenReturn(mockResp);

        ResponseEntity<TipoUsuarioResponse> response =
                controller.crear(authentication, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getId()).isEqualTo(1L);

        verify(tipoUsuarioFeignClient)
                .crear(any(CrearTipoUsuarioRequest.class));
    }

    @Test
    void obtenerPorIdTest() {
        Long id = 5L;
        TipoUsuarioResponse mockResp = buildTipoUsuario(id, "CLIENTE");

        when(tipoUsuarioFeignClient.obtenerPorId(id))
                .thenReturn(mockResp);

        ResponseEntity<TipoUsuarioResponse> response =
                controller.obtenerPorId(authentication, id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(id);

        verify(tipoUsuarioFeignClient).obtenerPorId(id);
    }

    @Test
    void listarTodosTest() {
        List<TipoUsuarioResponse> listaMock = List.of(
                buildTipoUsuario(1L, "ADMIN"),
                buildTipoUsuario(2L, "CLIENTE")
        );

        when(tipoUsuarioFeignClient.listarTodos())
                .thenReturn(listaMock);

        ResponseEntity<List<TipoUsuarioResponse>> response =
                controller.listarTodos(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);

        verify(tipoUsuarioFeignClient).listarTodos();
    }

    @Test
    void obtenerPorNombreTest() {
        String nombre = "VENDEDOR";
        TipoUsuarioResponse mockResp = buildTipoUsuario(3L, nombre);

        when(tipoUsuarioFeignClient.obtenerPorNombre(nombre))
                .thenReturn(mockResp);

        ResponseEntity<TipoUsuarioResponse> response =
                controller.obtenerPorNombre(authentication, nombre);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getNombreTipo()).isEqualTo(nombre);

        verify(tipoUsuarioFeignClient).obtenerPorNombre(nombre);
    }

    @Test
    void actualizarTipoUsuarioTest() {
        Long id = 10L;
        ActualizarTipoUsuarioRequest request = new ActualizarTipoUsuarioRequest();

        TipoUsuarioResponse actualizado = buildTipoUsuario(id, "ROL_MODIFICADO");

        when(tipoUsuarioFeignClient.actualizar(eq(id), any(ActualizarTipoUsuarioRequest.class)))
                .thenReturn(actualizado);

        ResponseEntity<TipoUsuarioResponse> response =
                controller.actualizar(authentication, id, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getNombreTipo()).isEqualTo("ROL_MODIFICADO");

        verify(tipoUsuarioFeignClient)
                .actualizar(eq(id), any(ActualizarTipoUsuarioRequest.class));
    }

    @Test
    void eliminarTipoUsuarioTest() {
        Long id = 9L;

        doNothing().when(tipoUsuarioFeignClient).eliminar(id);

        ResponseEntity<Void> response =
                controller.eliminar(authentication, id);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(tipoUsuarioFeignClient).eliminar(id);
    }
}