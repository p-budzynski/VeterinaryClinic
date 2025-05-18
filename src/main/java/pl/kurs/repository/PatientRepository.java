package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
