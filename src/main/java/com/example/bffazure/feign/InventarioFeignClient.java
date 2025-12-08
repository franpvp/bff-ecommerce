package com.example.bffazure.feign;


import com.example.bffazure.dto.ActualizarInventarioRequest;
import com.example.bffazure.dto.CrearInventarioRequest;
import com.example.bffazure.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "ms-inventarios",
        url = "${services.inventarios.url}"
)
public interface InventarioFeignClient {

    @PostMapping("/api/v1/inventarios")
    InventarioDTO crear(@RequestBody CrearInventarioRequest request);

    @GetMapping("/api/v1/inventarios/producto/{idProducto}")
    InventarioDTO obtenerPorProducto(@PathVariable("idProducto") Long idProducto);

    @PutMapping("/api/v1/inventarios/producto/{idProducto}")
    InventarioDTO actualizarCantidad(
            @PathVariable("idProducto") Long idProducto,
            @RequestBody ActualizarInventarioRequest request
    );

    @DeleteMapping("/api/v1/inventarios/{idInventario}")
    void eliminar(@PathVariable("idInventario") Long idInventario);
}
