package S3D1.demo.controllers;


import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // POST http://localhost:3001/dipendenti
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation) throws IOException {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        return dipendenteService.save(body);
    }


    // GET http://localhost:3001/dipendenti / {id}
    @GetMapping("/{id}")
    private Dipendente getSingleDipendente(@PathVariable int id){
        return dipendenteService.findById(id);
    }

    // **GET http://localhost:3001/dipendenti
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.listaDipendenti();
    }


}
