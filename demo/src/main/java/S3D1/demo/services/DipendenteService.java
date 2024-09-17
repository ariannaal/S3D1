package S3D1.demo.services;

import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.exceptions.NotFoundEx;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.repositories.DipendenteRepository;
import S3D1.demo.repositories.GestionePrenotazioniRepository;
import S3D1.demo.repositories.ViaggioRepository;


import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggiRepository;

    @Autowired
    private GestionePrenotazioniRepository gestionePrenotazioniRepository;

    @Autowired
    private PasswordEncoder bcrypt;


    public List<Dipendente> listaDipendenti() {

        return dipendenteRepository.findAll();
    }

    public Dipendente save(NewDipendenteDTO body) {

        this.dipendenteRepository.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestEx("L'email " + body.email() + " è già in uso!");
                }
        );


        Dipendente newDipendente = new Dipendente( bcrypt.encode(body.username()), body.nome(), body.cognome(), body.email());

        return this.dipendenteRepository.save(newDipendente);
    }


    public Dipendente findById(int id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public void findByIdAndDelete(int id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }

    public Dipendente findByIdAndUpdate(int id, Dipendente body) {
        Dipendente found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setImmagineProfilo(body.getImmagineProfilo());
        return dipendenteRepository.save(found);
    }

    public Dipendente findByEmail(String email) {
        return dipendenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundEx("Il dipendente con l'email " + email + " non è stato trovato!"));
    }


}
