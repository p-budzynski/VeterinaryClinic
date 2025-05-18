package pl.kurs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.AnimalDto;
import pl.kurs.dto.AnimalDtoList;
import pl.kurs.entity.Animal;
import pl.kurs.mapper.AnimalMapper;
import pl.kurs.service.AnimalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
@AllArgsConstructor
public class AnimalController {
    private AnimalService animalService;
    private AnimalMapper animalMapper;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnimalDto> getById(@PathVariable("id") Long id) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(a -> ResponseEntity.ok(animalMapper.entityToDto(a)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AnimalDto> getAll() {
        List<Animal> animals = animalService.getAll();
        return animalMapper.entitiesToDtos(animals);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public AnimalDtoList getAllXml() {
        List<Animal> animals = animalService.getAll();
        List<AnimalDto> dtos = animalMapper.entitiesToDtos(animals);
        return new AnimalDtoList(dtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDto createAnimal(@RequestBody AnimalDto animalDto) {
        Animal animal = animalMapper.dtoToEntity(animalDto);
        Animal savedAnimal = animalService.saveAnimal(animal);
        return animalMapper.entityToDto(savedAnimal);
    }

    @PutMapping
    public AnimalDto updateAnimal(@RequestBody AnimalDto animalDto) {
        Animal animal = animalMapper.dtoToEntityWithId(animalDto);
        Animal updatedAnimal = animalService.updateAnimal(animal);
        return animalMapper.entityToDto(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        animalService.deleteAnimalById(id);
    }

}
