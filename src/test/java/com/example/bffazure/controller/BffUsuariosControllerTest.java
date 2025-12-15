package com.example.bffazure.controller;

import com.example.bffazure.dto.*;
import com.example.bffazure.feign.UsuarioFeignClient;
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

class BffUsuariosControllerTest {

    @Mock
    private UsuarioFeignClient usuarioFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffUsuariosController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private UsuarioResponse buildUsuario(Long id, String username, String email) {
        return UsuarioResponse.builder()
                .id(id)
                .username(username)
                .email(email)
                .build();
    }

    @Test
    void crearUsuarioTest() {
        CrearUsuarioRequest request = new CrearUsuarioRequest();
        UsuarioResponse mockResp = buildUsuario(1L, "user.test", "user@test.com");

        when(usuarioFeignClient.crear(any(CrearUsuarioRequest.class)))
                .thenReturn(mockResp);

        ResponseEntity<UsuarioResponse> response =
                controller.crearUsuario(authentication, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getId()).isEqualTo(1L);

        verify(usuarioFeignClient).crear(any(CrearUsuarioRequest.class));
    }

    @Test
    void obtenerPorIdTest() {
        Long id = 5L;
        UsuarioResponse mockResp = buildUsuario(id, "fran", "fran@test.com");

        when(usuarioFeignClient.obtenerPorId(id))
                .thenReturn(mockResp);

        ResponseEntity<UsuarioResponse> response =
                controller.obtenerPorId(authentication, id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(id);

        verify(usuarioFeignClient).obtenerPorId(id);
    }

    @Test
    void listarTodosTest() {
        List<UsuarioResponse> mockList = List.of(
                buildUsuario(1L, "user1", "user1@test.com"),
                buildUsuario(2L, "user2", "user2@test.com")
        );

        when(usuarioFeignClient.listarTodos())
                .thenReturn(mockList);

        ResponseEntity<List<UsuarioResponse>> response =
                controller.listarTodos(authentication);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);

        verify(usuarioFeignClient).listarTodos();
    }

    @Test
    void obtenerPorEmailTest() {
        String email = "test@test.com";
        UsuarioResponse mockResp = buildUsuario(3L, "usuario.email", email);

        when(usuarioFeignClient.obtenerPorEmail(email))
                .thenReturn(mockResp);

        ResponseEntity<UsuarioResponse> response =
                controller.obtenerPorEmail(authentication, email);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getEmail()).isEqualTo(email);

        verify(usuarioFeignClient).obtenerPorEmail(email);
    }

    @Test
    void obtenerPorUsernameTest() {
        String username = "franvaldivia";
        UsuarioResponse mockResp = buildUsuario(10L, username, "fran@test.com");

        when(usuarioFeignClient.obtenerPorUsername(username))
                .thenReturn(mockResp);

        ResponseEntity<UsuarioResponse> response =
                controller.obtenerPorUsername(authentication, username);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getUsername()).isEqualTo(username);

        verify(usuarioFeignClient).obtenerPorUsername(username);
    }
}