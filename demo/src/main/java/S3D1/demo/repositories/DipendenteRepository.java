package S3D1.demo.repositories;


import S3D1.demo.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {

    Optional<Dipendente> findByEmail(String email);

}
