package S3D1.demo.services;

import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.NotFoundEx;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.repositories.DipendenteRepository;
import S3D1.demo.repositories.GestionePrenotazioniRepository;
import S3D1.demo.repositories.ViaggioRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<Dipendente> listaDipendenti() {

        return dipendenteRepository.findAll();
    }

    public Dipendente save(NewDipendenteDTO body) throws IOException {
        dipendenteRepository.findByEmail(body.email()).ifPresent(user -> {
            try {
                throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        Dipendente newDipendente = new Dipendente();
        newDipendente.setUsername(body.username());
        newDipendente.setNome(body.nome());
        newDipendente.setCognome(body.cognome());
        newDipendente.setEmail(body.email());

        Dipendente dipendenteSalvato = dipendenteRepository.save(newDipendente);
        System.out.println("Dipendente salvato con successo: " + dipendenteSalvato);

        return dipendenteSalvato;

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



}
