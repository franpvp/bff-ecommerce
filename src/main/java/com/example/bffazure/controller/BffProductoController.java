package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarProductoRequest;
import com.example.bffazure.dto.CrearProductoRequest;
import com.example.bffazure.feign.ProductoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/productos")
@CrossOrigin
@RequiredArgsConstructor
public class BffProductoController {

    private final ProductoFeignClient productoFeignClient;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(productoFeignClient.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {

        return ResponseEntity.ok(productoFeignClient.obtener(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearProductoRequest request,
                                   Authentication authentication) {

        return ResponseEntity.ok(productoFeignClient.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody ActualizarProductoRequest request,
                                        Authentication authentication) {

        return ResponseEntity.ok(productoFeignClient.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id,
                                      Authentication authentication) {

        productoFeignClient.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
