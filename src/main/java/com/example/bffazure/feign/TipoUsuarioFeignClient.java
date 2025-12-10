package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarTipoUsuarioRequest;
import com.example.bffazure.dto.CrearTipoUsuarioRequest;
import com.example.bffazure.dto.TipoUsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "tipo-usuario-service",
        url = "${services.tipoUsuario.url}"
)
public interface TipoUsuarioFeignClient {

    @PostMapping
    TipoUsuarioResponse crear(@RequestBody CrearTipoUsuarioRequest request);

    @GetMapping("/{id}")
    TipoUsuarioResponse obtenerPorId(@PathVariable Long id);

    @GetMapping
    List<TipoUsuarioResponse> listarTodos();

    @GetMapping("/buscar")
    TipoUsuarioResponse obtenerPorNombre(@RequestParam String nombreTipo);

    @PutMapping("/{id}")
    TipoUsuarioResponse actualizar(@PathVariable Long id, @RequestBody ActualizarTipoUsuarioRequest request);

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Long id);
}

