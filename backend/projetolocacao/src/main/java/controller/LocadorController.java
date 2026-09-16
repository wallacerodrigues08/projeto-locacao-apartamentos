package locacaoapartamentos.controller;

import locacaoapartamentos.model.Locador;
import locacaoapartamentos.service.LocadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LocadorController {

    private final LocadorService locadorService;

    public LocadorController(LocadorService locadorService) {
        this.locadorService = locadorService;
    }

    @GetMapping("/locadores")
    public List<Locador> listarTodos() {
        return locadorService.listarTodos();
    }

    @PostMapping("/locadores")
    public Locador salvar(@RequestBody Locador locador) {
        return locadorService.salvar(locador);
    }
}