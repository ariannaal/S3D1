package S3D1.demo.repositories;


import S3D1.demo.entities.Dipendente;
import S3D1.demo.entities.GestionePrenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface GestionePrenotazioniRepository extends JpaRepository<GestionePrenotazioni, Integer> {

    Optional<GestionePrenotazioni> findById(int id);

    // per verificare che un dipendente non prenoti lo stesso giorno
    Optional<GestionePrenotazioni> findByDipendenteAndDataPrenotazione(Dipendente dipendente, LocalDate dataPrenotazione);

}
