package pl.kurs.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kurs.entity.Appointment;
import pl.kurs.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment updateAppointment(Appointment appointment) {
        Appointment appointmentToUpdate = appointmentRepository.findById(appointment.getId()).orElseThrow();
        BeanUtils.copyProperties(appointment, appointmentToUpdate);
        return appointmentRepository.save(appointmentToUpdate);
    }

    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
