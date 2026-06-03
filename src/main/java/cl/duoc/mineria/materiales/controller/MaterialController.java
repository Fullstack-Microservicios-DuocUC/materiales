package cl.duoc.mineria.materiales.controller;

import cl.duoc.mineria.materiales.dto.RegistrarMaterialDTO;
import cl.duoc.mineria.materiales.model.Material;
import cl.duoc.mineria.materiales.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/registrar")
    public ResponseEntity<Material> registrar(@Valid @RequestBody RegistrarMaterialDTO dto) {
        return new ResponseEntity<>(materialService.registrarMaterial(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Material>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    // Endpoint de consumo vía WebClient
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.existeMaterial(id));
    }
}