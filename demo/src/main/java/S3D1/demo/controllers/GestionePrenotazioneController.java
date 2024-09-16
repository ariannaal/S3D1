package S3D1.demo.controllers;

import S3D1.demo.entities.GestionePrenotazioni;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.payloads.NewPrenotazioneDTO;
import S3D1.demo.services.GestionePrenotazioniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class GestionePrenotazioneController {

    @Autowired
    private GestionePrenotazioniService gestionePrenotazioniService;

    // POST http://localhost:3001/prenotazioni
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GestionePrenotazioni savePrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        return gestionePrenotazioniService.save(body);
    }

    // GET http://localhost:3001/prenotazioni / {id}
    @GetMapping("/{id}")
    private GestionePrenotazioni getSinglePrenotazione(@PathVariable int id){
        return gestionePrenotazioniService.findById(id);
    }

    // GET http://localhost:3001/prenotazioni
    @GetMapping
    public List<GestionePrenotazioni> getAllPrenotazioni() {
        return gestionePrenotazioniService.listaPrenotazioni();
    }
}
