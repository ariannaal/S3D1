package S3D1.demo.controllers;


import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //endpoint per risalire all’utente attualmente collegato
    @GetMapping("/me")
    public Dipendente getProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Dipendente updateProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedUser, @RequestBody Dipendente body) {
        return this.dipendenteService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedUser) {
        this.dipendenteService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }


    //se ha l’authority, solo l’admin (DIRIGENTE) puo’ cancellare l’utente
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRIGENTE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        this.dipendenteService.findByIdAndDelete(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRIGENTE')")
    public Dipendente findByIdAndUpdate(@PathVariable int userId, @RequestBody Dipendente body) {
        return this.dipendenteService.findByIdAndUpdate(userId, body);
    }





}
