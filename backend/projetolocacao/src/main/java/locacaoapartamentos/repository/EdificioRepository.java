package locacaoapartamentos.repository;

import locacaoapartamentos.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}