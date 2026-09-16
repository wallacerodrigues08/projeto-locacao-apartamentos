package locacaoapartamentos.service;

import locacaoapartamentos.model.Apartamento;
import locacaoapartamentos.model.Edificio;
import locacaoapartamentos.model.Locador;
import locacaoapartamentos.model.Locatario;
import locacaoapartamentos.repository.ApartamentoRepository;
import locacaoapartamentos.repository.EdificioRepository;
import locacaoapartamentos.repository.LocadorRepository;
import locacaoapartamentos.repository.LocatarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartamentoService {

    private final ApartamentoRepository apartamentoRepository;
    private final EdificioRepository edificioRepository;
    private final LocadorRepository locadorRepository;
    private final LocatarioRepository locatarioRepository;

    public ApartamentoService(
            ApartamentoRepository apartamentoRepository,
            EdificioRepository edificioRepository,
            LocadorRepository locadorRepository,
            LocatarioRepository locatarioRepository
    ) {
        this.apartamentoRepository = apartamentoRepository;
        this.edificioRepository = edificioRepository;
        this.locadorRepository = locadorRepository;
        this.locatarioRepository = locatarioRepository;
    }

    public Apartamento salvar(Apartamento apartamento) {

        if (apartamento.getEdificio() != null &&
                apartamento.getEdificio().getId() != null) {

            Edificio edificio = edificioRepository
                    .findById(apartamento.getEdificio().getId())
                    .orElseThrow(() -> new RuntimeException("Edifício não encontrado"));

            apartamento.setEdificio(edificio);
        }

        if (apartamento.getLocador() != null &&
                apartamento.getLocador().getId() != null) {

            Locador locador = locadorRepository
                    .findById(apartamento.getLocador().getId())
                    .orElseThrow(() -> new RuntimeException("Locador não encontrado"));

            apartamento.setLocador(locador);
        }

        return apartamentoRepository.save(apartamento);
    }

    public List<Apartamento> listarTodos() {
        return apartamentoRepository.findAll();
    }

    public List<Apartamento> listarDisponiveis() {
        return apartamentoRepository.findByDisponivelTrue();
    }

    public Optional<Apartamento> buscarPorId(Long id) {
        return apartamentoRepository.findById(id);
    }

    public Locatario buscarLocatario(Long apartamentoId) {

        Apartamento apartamento = apartamentoRepository.findById(apartamentoId)
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

        if (apartamento.getLocatario() == null) {
            throw new RuntimeException("Apartamento não possui locatário");
        }

        return apartamento.getLocatario();
    }

    public Apartamento alugar(Long apartamentoId, Locatario locatario) {

        Apartamento apartamento = apartamentoRepository.findById(apartamentoId)
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

        if (!apartamento.getDisponivel()) {
            throw new RuntimeException("Apartamento já está alugado");
        }

        Locatario locatarioSalvo = locatarioRepository.save(locatario);

        apartamento.setLocatario(locatarioSalvo);
        apartamento.setDisponivel(false);

        return apartamentoRepository.save(apartamento);
    }
}