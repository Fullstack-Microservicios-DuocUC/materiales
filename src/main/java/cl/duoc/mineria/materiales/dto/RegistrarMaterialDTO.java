package cl.duoc.mineria.materiales.dto;

import cl.duoc.mineria.materiales.model.ClasificacionMaterial;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrarMaterialDTO {

    @NotBlank(message = "El nombre del material (ej: Cobre Sulfuroso) no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotNull(message = "La densidad promedio del material es obligatoria")
    @Positive(message = "La densidad promedio debe ser un valor numérico positivo")
    private Double densidadPromedio;

    @NotNull(message = "La clasificación geológica del material es obligatoria")
    private ClasificacionMaterial clasificacion;
}