package com.escom.impuestos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escom.impuestos.model.EmpresarialEntity;
import com.escom.impuestos.service.EmpresarialService;


@RestController
@RequestMapping("/api/empresarial")
public class EmpresarialController {

    @Autowired
    private EmpresarialService empresarialService;

    @PostMapping("/addRegistro")
    public ResponseEntity<?> agregarRegistro(@RequestBody EmpresarialEntity empresarialEntity) {
        Boolean result = empresarialService.addRegistro(empresarialEntity);
        return ResponseEntity.ok(Map.of("success", result));
    }

    @GetMapping("/list/{idUsuario}")
    public ResponseEntity<?> getRegistrosPorUsuario(@PathVariable String idUsuario) {
        try {
            List<EmpresarialEntity> registros = empresarialService.getRegistrosPorUsuario(idUsuario);
            if (registros.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content si no hay resultados
            }
            return ResponseEntity.ok(registros);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Ocurrió un error al intentar obtener los registros: " + e.getMessage());
        }
    }
}