package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarClienteRequest;
import com.example.bffazure.dto.ClienteResponse;
import com.example.bffazure.dto.ContactoRequestDto;
import com.example.bffazure.dto.CrearClienteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "cliente-service",
        url = "${services.cliente.url}"
)
public interface ClienteFeignClient {

    @PostMapping
    ClienteResponse crear(@RequestBody CrearClienteRequest request);

    @GetMapping("/{id}")
    ClienteResponse obtenerPorId(@PathVariable Long id);

    @GetMapping("/email/{email}")
    ClienteResponse obtenerPorEmail(@PathVariable String email);

    @GetMapping
    List<ClienteResponse> listarTodos();

    @PutMapping("/{id}")
    ClienteResponse actualizar(@PathVariable Long id, @RequestBody ActualizarClienteRequest request);

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Long id);

    @PostMapping("/sync")
    ClienteResponse sincronizarClienteAutenticado();

    @PostMapping("/contacto")
    ResponseEntity<Void> enviar(@RequestBody ContactoRequestDto request);



}
