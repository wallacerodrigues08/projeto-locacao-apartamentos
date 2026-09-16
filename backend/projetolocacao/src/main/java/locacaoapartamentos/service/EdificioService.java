package locacaoapartamentos.service;

import locacaoapartamentos.model.Edificio;
import org.springframework.stereotype.Service;
import locacaoapartamentos.repository.EdificioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EdificioService {

    private final EdificioRepository edificioRepository;

    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    public Edificio salvar(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public List<Edificio> listarTodos() {
        return edificioRepository.findAll();
    }

    public Optional<Edificio> buscarPorId(Long id) {
        return edificioRepository.findById(id);
    }
}