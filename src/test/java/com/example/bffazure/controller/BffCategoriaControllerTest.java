package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarCategoriaRequest;
import com.example.bffazure.dto.CategoriaDTO;
import com.example.bffazure.dto.CrearCategoriaRequest;
import com.example.bffazure.feign.CategoriaFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BffCategoriaControllerTest {

    @Mock
    private CategoriaFeignClient categoriaFeignClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BffCategoriaController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCategoriaTest() {
        CrearCategoriaRequest request = new CrearCategoriaRequest();

        when(categoriaFeignClient.crear(request)).thenReturn(CategoriaDTO.builder().build());

        ResponseEntity<?> response = controller.crear(request, authentication);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        verify(categoriaFeignClient).crear(request);
    }

    @Test
    void actualizarCategoriaTest() {
        Long id = 5L;
        ActualizarCategoriaRequest request = new ActualizarCategoriaRequest();

        when(categoriaFeignClient.actualizar(id, request)).thenReturn(CategoriaDTO.builder().build());

        ResponseEntity<?> response = controller.actualizar(id, request, authentication);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void obtenerCategoriaPorIdTest() {

        Long id = 10L;

        when(categoriaFeignClient.obtenerPorId(id)).thenReturn(CategoriaDTO.builder().build());

        ResponseEntity<?> response = controller.obtener(id);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        verify(categoriaFeignClient).obtenerPorId(id);
    }

    @Test
    void listarCategoriasTest() {

        when(categoriaFeignClient.listar()).thenReturn(List.of(CategoriaDTO.builder().build()));

        ResponseEntity<?> response = controller.listar();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        verify(categoriaFeignClient).listar();
    }
}