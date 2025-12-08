package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarClienteRequest;
import com.example.bffazure.dto.ClienteResponse;
import com.example.bffazure.dto.CrearClienteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "cliente-service",
        url = "${services.cliente.url}"
)
public interface ClienteFeignClient {

    @PostMapping("/clientes")
    ClienteResponse crear(@RequestBody CrearClienteRequest request);

    @GetMapping("/clientes/{id}")
    ClienteResponse obtenerPorId(@PathVariable Long id);

    @GetMapping("/clientes")
    List<ClienteResponse> listarTodos();

    @PutMapping("/clientes/{id}")
        ClienteResponse actualizar(@PathVariable Long id, @RequestBody ActualizarClienteRequest request);

    @DeleteMapping("/clientes/{id}")
    void eliminar(@PathVariable Long id);
}
