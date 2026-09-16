package locacaoapartamentos.controller;

import locacaoapartamentos.model.Edificio;
import locacaoapartamentos.service.EdificioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EdificioController {

    private final EdificioService edificioService;

    public EdificioController(EdificioService edificioService) {
        this.edificioService = edificioService;
    }

    @GetMapping("/edificios")
    public List<Edificio> listarTodos() {
        return edificioService.listarTodos();
    }

    @PostMapping("/edificios")
    public Edificio salvar(@RequestBody Edificio edificio) {
        return edificioService.salvar(edificio);
    }
}