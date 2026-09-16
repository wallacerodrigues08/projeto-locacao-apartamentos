package locacaoapartamentos.controller;

import locacaoapartamentos.model.Apartamento;
import locacaoapartamentos.model.Locatario;
import locacaoapartamentos.service.ApartamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApartamentoController {

    private final ApartamentoService apartamentoService;

    public ApartamentoController(ApartamentoService apartamentoService) {
        this.apartamentoService = apartamentoService;
    }
    @GetMapping("/apartamentos/{id}/locatario")
    public Locatario buscarLocatario(@PathVariable Long id) {
        return apartamentoService.buscarLocatario(id);
    }
    @GetMapping("/apartamentos")
    public List<Apartamento> listarTodos() {
        return apartamentoService.listarTodos();
    }

    @GetMapping("/apartamentos/disponiveis")
    public List<Apartamento> listarDisponiveis() {
        return apartamentoService.listarDisponiveis();
    }

    @PostMapping("/apartamentos")
    public Apartamento salvar(@RequestBody Apartamento apartamento) {
        return apartamentoService.salvar(apartamento);
    }

    @PostMapping("/apartamentos/{id}/alugar")
    public Apartamento alugar(
            @PathVariable Long id,
            @RequestBody Locatario locatario
    ) {
        return apartamentoService.alugar(id, locatario);
    }
}