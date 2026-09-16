package locacaoapartamentos.service;

import locacaoapartamentos.model.Locatario;
import org.springframework.stereotype.Service;
import locacaoapartamentos.repository.LocatarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocatarioService {

    private final LocatarioRepository locatarioRepository;

    public LocatarioService(LocatarioRepository locatarioRepository) {
        this.locatarioRepository = locatarioRepository;
    }

    public Locatario salvar(Locatario locatario) {
        return locatarioRepository.save(locatario);
    }

    public List<Locatario> listarTodos() {
        return locatarioRepository.findAll();
    }

    public Optional<Locatario> buscarPorId(Long id) {
        return locatarioRepository.findById(id);
    }
}