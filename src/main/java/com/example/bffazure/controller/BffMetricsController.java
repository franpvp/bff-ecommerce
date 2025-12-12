package com.example.bffazure.controller;

import com.example.bffazure.dto.OrdenEstadoMetricDto;
import com.example.bffazure.feign.OrdenesFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/bff/metrics")
@RequiredArgsConstructor
public class BffMetricsController {

    private final OrdenesFeignClient ordenesFeignClient;

    @GetMapping("/ordenes/hoy")
    public ResponseEntity<List<OrdenEstadoMetricDto>> obtenerMetricasOrdenesHoy(Authentication authentication) {


        List<OrdenEstadoMetricDto> response = ordenesFeignClient.obtenerMetricasOrdenesHoy();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ordenes/hoy/correctas")
    public ResponseEntity<Long> obtenerCorrectasHoy(Authentication authentication) {


        Long response = ordenesFeignClient.obtenerCorrectasHoy();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/activos")
    public ResponseEntity<Long> obtenerUsuariosActivos(Authentication authentication) {
        ;

        Long response = ordenesFeignClient.obtenerUsuariosActivos();
        return ResponseEntity.ok(response);
    }
}