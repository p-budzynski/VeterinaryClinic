package pl.kurs.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kurs.entity.Animal;
import pl.kurs.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal updateAnimal(Animal animal) {
        Animal animalToUpdate = animalRepository.findById(animal.getId()).orElseThrow();
        BeanUtils.copyProperties(animal, animalToUpdate);
        return animalRepository.save(animalToUpdate);
    }

    public void deleteAnimalById(Long id) {
        animalRepository.deleteById(id);
    }
}
