package locacaoapartamentos.service;

import locacaoapartamentos.model.Locador;
import org.springframework.stereotype.Service;
import locacaoapartamentos.repository.LocadorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocadorService {

    private final LocadorRepository locadorRepository;

    public LocadorService(LocadorRepository locadorRepository) {
        this.locadorRepository = locadorRepository;
    }

    public Locador salvar(Locador locador) {
        return locadorRepository.save(locador);
    }

    public List<Locador> listarTodos() {
        return locadorRepository.findAll();
    }

    public Optional<Locador> buscarPorId(Long id) {
        return locadorRepository.findById(id);
    }
}