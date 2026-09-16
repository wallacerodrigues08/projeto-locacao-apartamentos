package locacaoapartamentos.repository;

import locacaoapartamentos.model.Apartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

    List<Apartamento> findByDisponivelTrue();

}