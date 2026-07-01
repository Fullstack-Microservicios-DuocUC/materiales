package cl.duoc.mineria.materiales.controller;

import cl.duoc.mineria.materiales.dto.RegistrarMaterialDTO;
import cl.duoc.mineria.materiales.model.Material;
import cl.duoc.mineria.materiales.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materiales")
@RequiredArgsConstructor
@Tag(name = "Gestión de Materiales", description = "Operaciones para administrar los tipos de materiales geológicos extraídos.")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar un nuevo tipo de material", description = "Crea un nuevo tipo de material geológico en el sistema (ej. Óxido, Sulfuro).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Material> registrar(@Valid @RequestBody RegistrarMaterialDTO dto) {
        return new ResponseEntity<>(materialService.registrarMaterial(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los materiales", description = "Obtiene una lista completa de todos los tipos de materiales registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de materiales obtenida con éxito")
    public ResponseEntity<List<Material>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un material por su ID", description = "Busca y devuelve un tipo de material específico a partir de su ID. Usado por otros servicios para obtener detalles como la clasificación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material encontrado"),
            @ApiResponse(responseCode = "404", description = "Material no encontrado con el ID proporcionado")
    })
    public ResponseEntity<Material> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    @GetMapping("/existe/{id}")
    @Operation(summary = "Verificar si un material existe", description = "Endpoint de utilidad para otros microservicios. Devuelve 'true' si el material con el ID existe, 'false' en caso contrario.")
    @ApiResponse(responseCode = "200", description = "Verificación realizada, el cuerpo de la respuesta contiene el booleano")
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.existeMaterial(id));
    }
}