package pl.kurs.controller;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.dto.PatientDto;
import pl.kurs.dto.PatientDtoList;
import pl.kurs.entity.Patient;
import pl.kurs.mapper.PatientMapper;
import pl.kurs.parser.PatientParser;
import pl.kurs.service.PatientService;
import pl.kurs.validation.Create;
import pl.kurs.validation.Update;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;
    private PatientMapper patientMapper;
    private PatientParser patientParser;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PatientDto> getById(@PathVariable("id") @Min(1) Long id) {
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

    @GetMapping("/search")
    public List<PatientDto> getByParams(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        List<Patient> patients = patientService.getByFirstNameAndLastName(firstName, lastName);
        return patientMapper.entitiesToDtos(patients);
    }

    @GetMapping(value = "/sort", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PatientDtoList getAllSortedByParams(
            @RequestParam(value = "property", defaultValue = "id") String property,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        List<Patient> patients = patientService.getAllSorted(property, sortDirection);
        return new PatientDtoList(patientMapper.entitiesToDtos(patients));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto createPatient(@RequestBody @Validated(Create.class) PatientDto patientDto) {
        Patient patient = patientMapper.dtoToEntity(patientDto);
        Patient savedPatient = patientService.savePatient(patient);
        return patientMapper.entityToDto(savedPatient);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadPatients(@RequestPart("file") MultipartFile file) throws IOException {
        List<PatientDto> patientsDtos = patientParser.parseCsv(file);
        List<Patient> patients = patientMapper.dtosToEntities(patientsDtos);
        patientService.uploadPatients(patients);
    }

    @PutMapping
    public PatientDto updatePatient(@RequestBody @Validated(Update.class) PatientDto patientDto) {
        Patient patient = patientMapper.dtoToEntity(patientDto);
        Patient updatedPatient = patientService.updatePatient(patient);
        return patientMapper.entityToDto(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @Min(1) Long id) {
        patientService.deletePatientById(id);
    }

}
