package cl.duoc.mineria.materiales.repository;

import cl.duoc.mineria.materiales.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Optional<Material> findByNombreIgnoreCase(String nombre);
}