package cl.duoc.mineria.materiales.service;

import cl.duoc.mineria.materiales.dto.RegistrarMaterialDTO;
import cl.duoc.mineria.materiales.exception.MaterialNotFoundException;
import cl.duoc.mineria.materiales.model.Material;
import cl.duoc.mineria.materiales.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Transactional
    public Material registrarMaterial(RegistrarMaterialDTO dto) {
        if (materialRepository.findByNombreIgnoreCase(dto.getNombre()).isPresent()) {
            throw new MaterialNotFoundException("El material '" + dto.getNombre() + "' ya existe en los registros geológicos.");
        }

        Material nuevoMaterial = Material.builder()
                .nombre(dto.getNombre())
                .densidadPromedio(dto.getDensidadPromedio())
                .clasificacion(dto.getClasificacion())
                .build();

        return materialRepository.save(nuevoMaterial);
    }

    @Transactional(readOnly = true)
    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Material obtenerPorId(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException("Material no catalogado con el ID: " + id));
    }

    @Transactional(readOnly = true)
    public boolean existeMaterial(Long id) {
        return materialRepository.existsById(id);
    }
}