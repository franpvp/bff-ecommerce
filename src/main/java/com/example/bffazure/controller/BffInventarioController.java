package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarInventarioRequest;
import com.example.bffazure.dto.CrearInventarioRequest;
import com.example.bffazure.feign.InventarioFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/inventarios")
@RequiredArgsConstructor
public class BffInventarioController {

    private final InventarioFeignClient inventarioFeignClient;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearInventarioRequest request,
                                   Authentication authentication) {

        return ResponseEntity.ok(inventarioFeignClient.crear(request));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<?> obtenerPorProducto(@PathVariable Long idProducto,
                                                Authentication authentication) {

        return ResponseEntity.ok(inventarioFeignClient.obtenerPorProducto(idProducto));
    }

    @PutMapping("/producto/{idProducto}")
    public ResponseEntity<?> actualizar(@PathVariable Long idProducto,
                                        @RequestBody ActualizarInventarioRequest request,
                                        Authentication authentication) {

        return ResponseEntity.ok(inventarioFeignClient.actualizarCantidad(idProducto, request));
    }

    @DeleteMapping("/{idInventario}")
    public ResponseEntity<?> eliminar(@PathVariable Long idInventario,
                                      Authentication authentication) {

        inventarioFeignClient.eliminar(idInventario);
        return ResponseEntity.noContent().build();
    }
}

