package com.example.bffazure.controller;

import com.example.bffazure.dto.ActualizarCategoriaRequest;
import com.example.bffazure.dto.CrearCategoriaRequest;
import com.example.bffazure.feign.CategoriaFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bff/categorias")
@RequiredArgsConstructor
public class BffCategoriaController {

    private final CategoriaFeignClient categoriaFeignClient;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearCategoriaRequest request,
                                   Authentication authentication) {

        return ResponseEntity.ok(categoriaFeignClient.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody ActualizarCategoriaRequest request,
                                        Authentication authentication) {
        return ResponseEntity.ok(categoriaFeignClient.actualizar(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaFeignClient.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(categoriaFeignClient.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id,
                                      Authentication authentication) {
        categoriaFeignClient.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

