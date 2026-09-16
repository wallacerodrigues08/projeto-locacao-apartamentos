package locacaoapartamentos.repository;

import locacaoapartamentos.model.Locatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
}