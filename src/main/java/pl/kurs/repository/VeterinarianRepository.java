package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
