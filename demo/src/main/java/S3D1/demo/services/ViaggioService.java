package S3D1.demo.services;

import S3D1.demo.entities.Viaggio;
import S3D1.demo.enums.StatoViaggio;
import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.exceptions.NotFoundEx;
import S3D1.demo.payloads.NewViaggioDTO;
import S3D1.demo.repositories.DipendenteRepository;
import S3D1.demo.repositories.GestionePrenotazioniRepository;
import S3D1.demo.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggiRepository;

    @Autowired
    private GestionePrenotazioniRepository gestionePrenotazioniRepository;

    @Autowired
    private DipendenteService dipendenteService;

    public Viaggio save(NewViaggioDTO body) {
        Viaggio newViaggio = new Viaggio();
        newViaggio.setDestinazione(body.destinazione());
        newViaggio.setDataViaggio(body.dataViaggio());
        newViaggio.setStatoViaggio(body.statoViaggio());

        Viaggio viaggioSalvato = viaggiRepository.save(newViaggio);
        System.out.println("Viaggio salvato con successo: " + viaggioSalvato);

        return viaggioSalvato;

    }


    public List<Viaggio> listaViaggi() {

        return viaggiRepository.findAll();
    }

    public Viaggio findById(int id) {
        return viaggiRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public void findByIdAndDelete(int id) {
        Viaggio found = this.findById(id);
        viaggiRepository.delete(found);
    }

    public Viaggio findByIdAndUpdate(int id, NewViaggioDTO body) {
        Viaggio found = this.findById(id);

        found.setDestinazione(body.destinazione());
        found.setDataViaggio(body.dataViaggio());
        found.setStatoViaggio(body.statoViaggio());

        return viaggiRepository.save(found);
    }

    public Viaggio updateStatoViaggio(int id, StatoViaggio statoViaggio){
        Viaggio viaggio = viaggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundEx("Viaggio con id " + id + " non trovato"));

        if (viaggio.getStatoViaggio() == StatoViaggio.COMPLETATO) {
            throw new BadRequestEx("Il viaggio con id " + id + " e' gia' completato e non puo' essere aggiornato.");
        }

        viaggio.setStatoViaggio(statoViaggio);
        return viaggiRepository.save(viaggio);
    }

}
