package com.example.bffazure.controller;

import com.example.bffazure.dto.OrdenDto;
import com.example.bffazure.dto.OrdenEstadoDto;
import com.example.bffazure.feign.OrdenesFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/bff/ordenes")
@RequiredArgsConstructor
public class BffOrdenesController {

    private final OrdenesFeignClient ordenesFeignClient;

    @PostMapping
    public ResponseEntity<OrdenDto> crearOrden(Authentication authentication,
                                               @RequestBody OrdenDto request) {
        String usuario = authentication.getName();

        log.info("Usuario :", usuario);
        OrdenDto response = ordenesFeignClient.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Obtener la última orden del cliente usando su idCliente
     */
    @GetMapping("/cliente/{idCliente}/ultima")
    public ResponseEntity<?> obtenerUltimaOrden(Authentication authentication,
                                                @PathVariable Long idCliente) {

        String usuario = authentication.getName();
        log.info("Usuario :", usuario);
        OrdenEstadoDto ultimaOrden = ordenesFeignClient.obtenerUltimaOrden(idCliente);
        return ResponseEntity.ok(ultimaOrden);
    }

    @GetMapping("/{idOrden}")
    public ResponseEntity<OrdenDto> buscarOrdenPorId(@PathVariable Long idOrden) {
        OrdenDto dto = ordenesFeignClient.buscarOrdenPorId(idOrden);
        return ResponseEntity.ok(dto);
    }


}
