package S3D1.demo.controllers;

import S3D1.demo.entities.Viaggio;
import S3D1.demo.enums.StatoViaggio;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.payloads.NewViaggioDTO;
import S3D1.demo.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    ViaggioService viaggioService;

    // POST http://localhost:3001/viaggi
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }

        return viaggioService.save(body);
    }

    // GET http://localhost:3001/viaggi / {id}
        @GetMapping("/{id}")
        private Viaggio getSingleViaggio(@PathVariable int id){
            return viaggioService.findById(id);
        }

    // GET http://localhost:3001/viaggi
    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.listaViaggi();
    }

    // PUT http://localhost:3001/viaggi/{id}/stato?statoViaggio=COMPLETATO
    @PutMapping("/{id}/stato")
    public Viaggio updateStatoViaggio (@PathVariable int id, @RequestParam StatoViaggio statoViaggio) {

        return viaggioService.updateStatoViaggio(id, statoViaggio);
    }



}
