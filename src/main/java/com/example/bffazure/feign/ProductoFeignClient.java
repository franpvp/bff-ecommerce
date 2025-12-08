package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarProductoRequest;
import com.example.bffazure.dto.CrearProductoRequest;
import com.example.bffazure.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "ms-productos",
        url = "${services.productos.url}"
)
public interface ProductoFeignClient {

    @GetMapping("/api/v1/productos")
    List<ProductoDTO> listar();

    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO obtener(@PathVariable("id") Long id);

    @PostMapping("/api/v1/productos")
    ProductoDTO crear(@RequestBody CrearProductoRequest request);

    @PutMapping("/api/v1/productos/{id}")
    ProductoDTO actualizar(
            @PathVariable("id") Long id,
            @RequestBody ActualizarProductoRequest request
    );

    @DeleteMapping("/api/v1/productos/{id}")
    void eliminar(@PathVariable("id") Long id);
}
