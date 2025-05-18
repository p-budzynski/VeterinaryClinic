package pl.kurs.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kurs.entity.Veterinarian;
import pl.kurs.repository.VeterinarianRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;

    public Optional<Veterinarian> getVeterinarianById(Long id) {
        return veterinarianRepository.findById(id);
    }

    public List<Veterinarian> getAll() {
        return veterinarianRepository.findAll();
    }

    public Veterinarian saveVeterinarian(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    @Transactional
    public Veterinarian updateVeterinarian(Veterinarian veterinarian) {
        Veterinarian veterinarianToUpdate = veterinarianRepository.findById(veterinarian.getId()).orElseThrow();
        BeanUtils.copyProperties(veterinarian, veterinarianToUpdate);
        return veterinarianRepository.save(veterinarianToUpdate);
    }

    public void deleteVeterinarianById(Long id) {
        veterinarianRepository.deleteById(id);
    }

}

