package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarTipoUsuarioRequest;
import com.example.bffazure.dto.CrearTipoUsuarioRequest;
import com.example.bffazure.dto.TipoUsuarioResponse;
import com.example.bffazure.feign.TipoUsuarioFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bff/roles")
@RequiredArgsConstructor
@CrossOrigin
public class BffTipoUsuarioController {

    private final TipoUsuarioFeignClient tipoUsuarioFeignClient;

    /**
     * Crear tipo de usuario
     */
    @PostMapping
    public ResponseEntity<TipoUsuarioResponse> crear(Authentication authentication,
                                                     @RequestBody CrearTipoUsuarioRequest request) {


        TipoUsuarioResponse response = tipoUsuarioFeignClient.crear(request);

        log.info("[BFF-TIPO-USUARIO] Tipo de usuario creado con ID {}", response.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener tipo de usuario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> obtenerPorId(Authentication authentication,
                                                            @PathVariable Long id) {


        TipoUsuarioResponse response = tipoUsuarioFeignClient.obtenerPorId(id);

        log.info("[BFF-TIPO-USUARIO] Tipo de usuario ID {} obtenido correctamente", id);

        return ResponseEntity.ok(response);
    }

    /**
     * Listar todos los tipos de usuario
     */
    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponse>> listarTodos(Authentication authentication) {


        List<TipoUsuarioResponse> lista = tipoUsuarioFeignClient.listarTodos();

        log.info("[BFF-TIPO-USUARIO] Se retornaron {} tipos de usuario", lista.size());

        return ResponseEntity.ok(lista);
    }

    /**
     * Buscar tipo de usuario por nombre
     */
    @GetMapping("/buscar")
    public ResponseEntity<TipoUsuarioResponse> obtenerPorNombre(Authentication authentication,
                                                                @RequestParam String nombreTipo) {


        TipoUsuarioResponse response = tipoUsuarioFeignClient.obtenerPorNombre(nombreTipo);

        log.info("[BFF-TIPO-USUARIO] Tipo de usuario '{}' encontrado con ID {}",
                nombreTipo, response.getId());

        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar tipo de usuario
     */
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> actualizar(Authentication authentication,
                                                          @PathVariable Long id,
                                                          @RequestBody ActualizarTipoUsuarioRequest request) {


        TipoUsuarioResponse actualizado = tipoUsuarioFeignClient.actualizar(id, request);

        log.info("[BFF-TIPO-USUARIO] Tipo de usuario ID {} actualizado correctamente", id);

        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar tipo de usuario
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(Authentication authentication,
                                         @PathVariable Long id) {


        tipoUsuarioFeignClient.eliminar(id);

        log.info("[BFF-TIPO-USUARIO] Tipo de usuario ID {} eliminado correctamente", id);

        return ResponseEntity.noContent().build();
    }
}
