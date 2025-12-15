package com.example.bffazure.controller;

import com.example.bffazure.dto.*;
import com.example.bffazure.feign.ClienteFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BffClientesControllerTest {

    @Mock
    private ClienteFeignClient clienteFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffClientesController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private ClienteResponse buildClienteResponse(Long id) {

        TipoUsuarioDTO tipo = new TipoUsuarioDTO(
                1L,
                "ADMIN"
        );

        UsuarioDTO usuario = new UsuarioDTO(
                id,
                "usuario.test",
                "usuario@test.com",
                tipo
        );

        return ClienteResponse.builder()
                .id(id)
                .idUsuario(id)
                .nombre("Francisca")
                .apellido("Valdivia")
                .telefono("91231234")
                .direccion("Santiago Centro")
                .ciudad("Santiago")
                .fechaRegistro(LocalDateTime.now())
                .usuario(usuario)
                .build();
    }

    @Test
    void crearClienteTest() {
        CrearClienteRequest request = new CrearClienteRequest();
        ClienteResponse clienteMock = buildClienteResponse(1L);

        when(clienteFeignClient.crear(request)).thenReturn(clienteMock);

        ResponseEntity<ClienteResponse> response =
                controller.crearCliente(authentication, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(clienteMock);
        verify(clienteFeignClient).crear(request);
    }

    @Test
    void obtenerClientePorIdTest() {
        Long id = 5L;
        ClienteResponse clienteMock = buildClienteResponse(id);

        when(clienteFeignClient.obtenerPorId(id)).thenReturn(clienteMock);

        ResponseEntity<ClienteResponse> response =
                controller.obtenerPorId(authentication, id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(id);
        verify(clienteFeignClient).obtenerPorId(id);
    }

    @Test
    void obtenerClientePorEmailTest() {
        String email = "usuario@test.com";
        ClienteResponse clienteMock = buildClienteResponse(3L);

        when(clienteFeignClient.obtenerPorEmail(email)).thenReturn(clienteMock);

        ResponseEntity<ClienteResponse> response =
                controller.obtenerPorEmail(email);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getUsuario().getEmail())
                .isEqualTo("usuario@test.com");

        verify(clienteFeignClient).obtenerPorEmail(email);
    }

    @Test
    void listarTodosTest() {
        List<ClienteResponse> listaMock = List.of(
                buildClienteResponse(1L),
                buildClienteResponse(2L)
        );

        when(clienteFeignClient.listarTodos()).thenReturn(listaMock);

        ResponseEntity<List<ClienteResponse>> response =
                controller.listarTodos(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
        verify(clienteFeignClient).listarTodos();
    }

    @Test
    void actualizarClienteTest() {
        Long id = 10L;
        ActualizarClienteRequest request = new ActualizarClienteRequest();

        ClienteResponse modificado = buildClienteResponse(id);
        modificado.setNombre("Nombre Modificado");

        when(clienteFeignClient.actualizar(id, request)).thenReturn(modificado);

        ResponseEntity<ClienteResponse> response =
                controller.actualizar(authentication, id, request);

        assertThat(response.getBody().getNombre()).isEqualTo("Nombre Modificado");
        verify(clienteFeignClient).actualizar(id, request);
    }

    @Test
    void eliminarClienteTest() {
        Long id = 99L;
        doNothing().when(clienteFeignClient).eliminar(id);

        ResponseEntity<Void> response =
                controller.eliminar(authentication, id);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(clienteFeignClient).eliminar(id);
    }

    @Test
    void sincronizarClienteAutenticadoTest() {
        ClienteResponse mock = buildClienteResponse(5L);

        when(clienteFeignClient.sincronizarClienteAutenticado()).thenReturn(mock);

        ClienteResponse result = controller.sincronizarClienteAutenticado();

        assertThat(result.getId()).isEqualTo(5L);
        verify(clienteFeignClient).sincronizarClienteAutenticado();
    }

}