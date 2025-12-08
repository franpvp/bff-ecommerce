package com.example.bffazure.feign;

import com.example.bffazure.dto.CrearUsuarioRequest;
import com.example.bffazure.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "usuario-service",
        url = "${services.usuario.url}"
)
public interface UsuarioFeignClient {

    @PostMapping("/usuarios")
    UsuarioResponse crear(@RequestBody CrearUsuarioRequest request);

    @GetMapping("/usuarios/{id}")
    UsuarioResponse obtenerPorId(@PathVariable Long id);

    @GetMapping("/usuarios")
    List<UsuarioResponse> listarTodos();

    @GetMapping("/usuarios/email/{email}")
    UsuarioResponse obtenerPorEmail(@PathVariable String email);

    @GetMapping("/usuarios/username/{username}")
    UsuarioResponse obtenerPorUsername(@PathVariable String username);
}

