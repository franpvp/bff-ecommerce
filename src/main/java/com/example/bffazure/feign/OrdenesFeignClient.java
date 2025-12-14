package com.example.bffazure.feign;

import com.example.bffazure.config.FeignAuthInterceptor;
import com.example.bffazure.dto.OrdenDto;
import com.example.bffazure.dto.OrdenEstadoDto;
import com.example.bffazure.dto.OrdenEstadoMetricDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "ms-ordenes",
        url = "${services.ordenes.url}",
        configuration = FeignAuthInterceptor.class
)
public interface OrdenesFeignClient {

    @PostMapping("/api/v1/ordenes")
    OrdenDto crear(@RequestBody OrdenDto request);

    @GetMapping("/api/v1/ordenes/cliente/{idCliente}/ultima")
    OrdenEstadoDto obtenerUltimaOrden(@PathVariable Long idCliente);

    @GetMapping("/api/v1/ordenes/{idOrden}")
    OrdenDto buscarOrdenPorId(@PathVariable Long idOrden);

    @GetMapping("/api/v1/metrics/ordenes/hoy")
    List<OrdenEstadoMetricDto> obtenerMetricasOrdenesHoy();

    @GetMapping("/api/v1/metrics/ordenes/hoy/correctas")
    Long obtenerCorrectasHoy();

    @GetMapping("/api/v1/metrics/usuarios/activos")
    Long obtenerUsuariosActivos();

    @GetMapping("/api/v1/ordenes/cliente/{idCliente}/historial")
    List<OrdenDto> obtenerHistorialCliente(@PathVariable Long idCliente);

}
