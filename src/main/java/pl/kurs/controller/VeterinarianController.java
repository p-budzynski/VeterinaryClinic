package pl.kurs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.VeterinarianDto;
import pl.kurs.dto.VeterinarianDtoList;
import pl.kurs.entity.Veterinarian;
import pl.kurs.mapper.VeterinarianMapper;
import pl.kurs.service.VeterinarianService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veterinarians")
@AllArgsConstructor
public class VeterinarianController {
    private VeterinarianService veterinarianService;
    private VeterinarianMapper veterinarianMapper;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<VeterinarianDto> getById(@PathVariable("id") Long id) {
        Optional<Veterinarian> veterinarian = veterinarianService.getVeterinarianById(id);
        return veterinarian.map(v -> ResponseEntity.ok(veterinarianMapper.entityToDto(v)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VeterinarianDto> getAll() {
        List<Veterinarian> veterinarians = veterinarianService.getAll();
        return veterinarianMapper.entitiesToDtos(veterinarians);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public VeterinarianDtoList getAllXml() {
        List<Veterinarian> veterinarians = veterinarianService.getAll();
        List<VeterinarianDto> dtos = veterinarianMapper.entitiesToDtos(veterinarians);
        return new VeterinarianDtoList(dtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeterinarianDto createVeterinarian(@RequestBody VeterinarianDto veterinarianDto) {
        Veterinarian veterinarian = veterinarianMapper.dtoToEntity(veterinarianDto);
        Veterinarian savedVeterinarian = veterinarianService.saveVeterinarian(veterinarian);
        return veterinarianMapper.entityToDto(savedVeterinarian);
    }

    @PutMapping
    public VeterinarianDto updateVeterinarian(@RequestBody VeterinarianDto veterinarianDto) {
        Veterinarian veterinarian = veterinarianMapper.dtoToEntityWithId(veterinarianDto);
        Veterinarian updatedVeterinarian = veterinarianService.updateVeterinarian(veterinarian);
        return veterinarianMapper.entityToDto(updatedVeterinarian);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        veterinarianService.deleteVeterinarianById(id);
    }

}
