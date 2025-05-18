package pl.kurs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.PatientDto;
import pl.kurs.dto.PatientDtoList;
import pl.kurs.entity.Patient;
import pl.kurs.mapper.PatientMapper;
import pl.kurs.service.PatientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;
    private PatientMapper patientMapper;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PatientDto> getById(@PathVariable("id") Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(p -> ResponseEntity.ok(patientMapper.entityToDto(p)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PatientDto> getAll() {
        List<Patient> patients = patientService.getAll();
        return patientMapper.entitiesToDtos(patients);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PatientDtoList getAllXml() {
        List<Patient> patients = patientService.getAll();
        List<PatientDto> dtos = patientMapper.entitiesToDtos(patients);
        return new PatientDtoList(dtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto createPatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientMapper.dtoToEntity(patientDto);
        Patient savedPatient = patientService.savePatient(patient);
        return patientMapper.entityToDto(savedPatient);
    }

    @PutMapping
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientMapper.dtoToEntity(patientDto);
        Patient updatedPatient = patientService.updatePatient(patient);
        return patientMapper.entityToDto(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        patientService.deletePatientById(id);
    }

}
