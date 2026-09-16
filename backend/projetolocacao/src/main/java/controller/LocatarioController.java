package locacaoapartamentos.controller;

import locacaoapartamentos.model.Locatario;
import locacaoapartamentos.service.LocatarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LocatarioController {

    private final LocatarioService locatarioService;

    public LocatarioController(LocatarioService locatarioService) {
        this.locatarioService = locatarioService;
    }

    @GetMapping("/locatarios")
    public List<Locatario> listarTodos() {
        return locatarioService.listarTodos();
    }

    @PostMapping("/locatarios")
    public Locatario salvar(@RequestBody Locatario locatario) {
        return locatarioService.salvar(locatario);
    }
}