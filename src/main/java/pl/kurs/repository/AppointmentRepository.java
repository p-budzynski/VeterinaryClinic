package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
