package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarUsuarioRequest;
import com.example.bffazure.dto.CrearUsuarioRequest;
import com.example.bffazure.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(
        name = "usuario-service",
        url = "${services.usuario.url}"
)
public interface UsuarioFeignClient {

    @PostMapping
    UsuarioResponse crear(@RequestBody CrearUsuarioRequest request);

    @GetMapping("/{id}")
    UsuarioResponse obtenerPorId(@PathVariable Long id);

    @GetMapping
    List<UsuarioResponse> listarTodos();

    @GetMapping("/email/{email}")
    UsuarioResponse obtenerPorEmail(@PathVariable String email);

    @GetMapping("/username/{username}")
    UsuarioResponse obtenerPorUsername(@PathVariable String username);

    @PutMapping("/{id}")
    UsuarioResponse actualizar(@PathVariable Long id, @RequestBody ActualizarUsuarioRequest request
    );

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Long id);
}

