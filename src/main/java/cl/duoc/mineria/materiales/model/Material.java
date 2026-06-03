package cl.duoc.mineria.materiales.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materiales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "densidad_promedio", nullable = false)
    private Double densidadPromedio; // Expresado en t/m³

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ClasificacionMaterial clasificacion;
}