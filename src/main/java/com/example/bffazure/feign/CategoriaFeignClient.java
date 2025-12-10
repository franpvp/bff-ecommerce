package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarCategoriaRequest;
import com.example.bffazure.dto.CategoriaDTO;
import com.example.bffazure.dto.CrearCategoriaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "ms-categorias",
        url = "${services.categorias.url}"
)
public interface CategoriaFeignClient {

    @PostMapping
    CategoriaDTO crear(@RequestBody CrearCategoriaRequest request);

    @PutMapping("/{id}")
    CategoriaDTO actualizar(
            @PathVariable("id") Long id,
            @RequestBody ActualizarCategoriaRequest request
    );

    @GetMapping("/{id}")
    CategoriaDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping
    List<CategoriaDTO> listar();

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable("id") Long id);
}
