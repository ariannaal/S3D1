package S3D1.demo.repositories;

import S3D1.demo.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViaggioRepository extends JpaRepository<Viaggio, Integer>  {

    Optional<Viaggio> findById(int id);


}
