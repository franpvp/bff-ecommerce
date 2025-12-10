package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarProductoRequest;
import com.example.bffazure.dto.CrearProductoRequest;
import com.example.bffazure.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "ms-productos",
        url = "${services.productos.url}"
)
public interface ProductoFeignClient {

    @GetMapping
    List<ProductoDTO> listar();

    @GetMapping("/{id}")
    ProductoDTO obtener(@PathVariable("id") Long id);

    @PostMapping
    ProductoDTO crear(@RequestBody CrearProductoRequest request);

    @PutMapping("/{id}")
    ProductoDTO actualizar(
            @PathVariable("id") Long id,
            @RequestBody ActualizarProductoRequest request
    );

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable("id") Long id);
}
