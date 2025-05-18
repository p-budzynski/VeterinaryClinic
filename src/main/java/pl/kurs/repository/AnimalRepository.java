package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
