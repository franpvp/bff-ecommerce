package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarClienteRequest;
import com.example.bffazure.dto.ClienteResponse;
import com.example.bffazure.dto.CrearClienteRequest;
import com.example.bffazure.feign.ClienteFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bff/clientes")
@RequiredArgsConstructor
public class BffClientesController {

    private final ClienteFeignClient clienteFeignClient;

    /**
     * Crear cliente
     */
    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(Authentication authentication,
                                                        @RequestBody CrearClienteRequest request) {

        log.info("[BFF-CLIENTES] Usuario '{}' solicitó crear cliente", authentication.getName());
        ClienteResponse cliente = clienteFeignClient.crear(request);
        log.info("[BFF-CLIENTES] Cliente creado con ID {}", cliente.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    /**
     * Obtener cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerPorId(Authentication authentication,
                                                        @PathVariable Long id) {

        log.info("[BFF-CLIENTES] Usuario '{}' solicitó obtener cliente ID {}", authentication.getName(), id);
        ClienteResponse cliente = clienteFeignClient.obtenerPorId(id);
        log.info("[BFF-CLIENTES] Cliente ID {} obtenido correctamente", id);

        return ResponseEntity.ok(cliente);
    }

    /**
     * Obtener cliente por email
     */

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponse> obtenerPorEmail(@PathVariable String email){
        log.info("[BFF-CLIENTES] Cliente '{}' solicitó obtener el email ", email);
        ClienteResponse cliente = clienteFeignClient.obtenerPorEmail(email);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Listar todos los clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodos(Authentication authentication) {

        log.info("[BFF-CLIENTES] Usuario '{}' solicitó listar todos los clientes", authentication.getName());
        List<ClienteResponse> lista = clienteFeignClient.listarTodos();
        log.info("[BFF-CLIENTES] Se retornaron {} clientes", lista.size());

        return ResponseEntity.ok(lista);
    }

    /**
     * Actualizar cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(Authentication authentication,
                                                      @PathVariable Long id,
                                                      @RequestBody ActualizarClienteRequest request) {

        log.info("[BFF-CLIENTES] Usuario '{}' solicitó actualizar cliente ID {}", authentication.getName(), id);
        ClienteResponse actualizado = clienteFeignClient.actualizar(id, request);
        log.info("[BFF-CLIENTES] Cliente ID {} actualizado exitosamente", id);

        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(Authentication authentication,
                                         @PathVariable Long id) {

        log.info("[BFF-CLIENTES] Usuario '{}' solicitó eliminar cliente ID {}", authentication.getName(), id);
        clienteFeignClient.eliminar(id);
        log.info("[BFF-CLIENTES] Cliente ID {} eliminado correctamente", id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sync")
    public ClienteResponse sincronizarClienteAutenticado() {
        return clienteFeignClient.sincronizarClienteAutenticado();
    }

}
