package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarClienteRequest;
import com.example.bffazure.dto.CrearUsuarioRequest;
import com.example.bffazure.dto.UsuarioResponse;
import com.example.bffazure.feign.UsuarioFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bff/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class BffUsuariosController {

    private final UsuarioFeignClient usuarioFeignClient;

    /**
     * Crear usuario
     */
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(Authentication authentication,
                                                        @RequestBody CrearUsuarioRequest request) {


        UsuarioResponse response = usuarioFeignClient.crear(request);

        log.info("[BFF-USUARIOS] Usuario creado con ID {}", response.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(Authentication authentication,
                                                        @PathVariable Long id) {


        UsuarioResponse response = usuarioFeignClient.obtenerPorId(id);

        log.info("[BFF-USUARIOS] Usuario ID {} obtenido correctamente", id);

        return ResponseEntity.ok(response);
    }

    /**
     * Listar todos los usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos(Authentication authentication) {


        List<UsuarioResponse> lista = usuarioFeignClient.listarTodos();

        log.info("[BFF-USUARIOS] Se retornaron {} usuarios", lista.size());

        return ResponseEntity.ok(lista);
    }

    /**
     * Buscar usuario por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponse> obtenerPorEmail(Authentication authentication,
                                                           @PathVariable String email) {


        UsuarioResponse response = usuarioFeignClient.obtenerPorEmail(email);

        log.info("[BFF-USUARIOS] Usuario con email '{}' obtenido exitosamente", email);

        return ResponseEntity.ok(response);
    }

    /**
     * Buscar usuario por username
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioResponse> obtenerPorUsername(Authentication authentication,
                                                              @PathVariable String username) {


        UsuarioResponse response = usuarioFeignClient.obtenerPorUsername(username);

        log.info("[BFF-USUARIOS] Usuario con username '{}' obtenido exitosamente", username);

        return ResponseEntity.ok(response);
    }


}
