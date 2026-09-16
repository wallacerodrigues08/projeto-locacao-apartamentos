package locacaoapartamentos.repository;

import locacaoapartamentos.model.Locador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocadorRepository extends JpaRepository<Locador, Long> {
}