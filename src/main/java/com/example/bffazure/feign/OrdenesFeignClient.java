package com.example.bffazure.feign;

import com.example.bffazure.dto.OrdenDto;
import com.example.bffazure.dto.OrdenEstadoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-ordenes",
        url = "${services.ordenes.url}"
)
public interface OrdenesFeignClient {

    @PostMapping("/ordenes")
    OrdenDto crear(@RequestBody OrdenDto request);

    @GetMapping("/ordenes/cliente/{idCliente}/ultima")
    OrdenEstadoDto obtenerUltimaOrden(@PathVariable Long idCliente);

}
