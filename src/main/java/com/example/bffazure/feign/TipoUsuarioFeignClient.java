package com.example.bffazure.feign;

import com.example.bffazure.dto.ActualizarTipoUsuarioRequest;
import com.example.bffazure.dto.CrearTipoUsuarioRequest;
import com.example.bffazure.dto.TipoUsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "tipo-usuario-service",
        url = "${services.tipoUsuario.url}"
)
public interface TipoUsuarioFeignClient {

    @PostMapping("/tipo-usuario")
    TipoUsuarioResponse crear(@RequestBody CrearTipoUsuarioRequest request);

    @GetMapping("/tipo-usuario/{id}")
    TipoUsuarioResponse obtenerPorId(@PathVariable Long id);

    @GetMapping("/tipo-usuario")
    List<TipoUsuarioResponse> listarTodos();

    @GetMapping("/tipo-usuario/buscar")
    TipoUsuarioResponse obtenerPorNombre(@RequestParam String nombreTipo);

    @PutMapping("/tipo-usuario/{id}")
    TipoUsuarioResponse actualizar(@PathVariable Long id, @RequestBody ActualizarTipoUsuarioRequest request);

    @DeleteMapping("/tipo-usuario/{id}")
    void eliminar(@PathVariable Long id);
}

