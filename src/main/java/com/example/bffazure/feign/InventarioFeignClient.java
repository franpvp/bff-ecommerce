package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarInventarioRequest;
import com.example.bffazure.dto.CrearInventarioRequest;
import com.example.bffazure.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "ms-inventarios",
        url = "${services.inventarios.url}"
)
public interface InventarioFeignClient {

    @GetMapping
    List<InventarioDTO> listar();

    @PostMapping
    InventarioDTO crear(@RequestBody CrearInventarioRequest request);

    @GetMapping("/producto/{idProducto}")
    InventarioDTO obtenerPorProducto(@PathVariable("idProducto") Long idProducto);

    @PutMapping("/producto/{idProducto}")
    InventarioDTO actualizarCantidad(
            @PathVariable("idProducto") Long idProducto,
            @RequestBody ActualizarInventarioRequest request
    );

    @DeleteMapping("/{idInventario}")
    void eliminar(@PathVariable("idInventario") Long idInventario);
}
