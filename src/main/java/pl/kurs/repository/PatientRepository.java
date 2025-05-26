package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p from Patient p where (p.firstName = :firstName or :firstName is null) and (p.lastName = :lastName or :lastName is null)")
    List<Patient> findAllByFirstNameAndLastName(String firstName, String lastName);
}
