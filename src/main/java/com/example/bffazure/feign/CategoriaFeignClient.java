package com.example.bffazure.feign;


import com.example.bffazure.dto.ActualizarCategoriaRequest;
import com.example.bffazure.dto.CategoriaDTO;
import com.example.bffazure.dto.CrearCategoriaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "ms-categorias",
        url = "${services.categorias.url}"
)
public interface CategoriaFeignClient {

    @PostMapping("/api/v1/categorias")
    CategoriaDTO crear(@RequestBody CrearCategoriaRequest request);

    @PutMapping("/api/v1/categorias/{id}")
    CategoriaDTO actualizar(
            @PathVariable("id") Long id,
            @RequestBody ActualizarCategoriaRequest request
    );

    @GetMapping("/api/v1/categorias/{id}")
    CategoriaDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/api/v1/categorias")
    List<CategoriaDTO> listar();

    @DeleteMapping("/api/v1/categorias/{id}")
    void eliminar(@PathVariable("id") Long id);
}
