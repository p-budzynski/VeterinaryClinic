package pl.kurs.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kurs.entity.Patient;
import pl.kurs.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Patient patient) {
        Patient patientToUpdate = patientRepository.findById(patient.getId()).orElseThrow();
        BeanUtils.copyProperties(patient, patientToUpdate);
        return patientRepository.save(patientToUpdate);
    }

    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
