package pl.kurs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kurs.dto.VeterinarianDto;
import pl.kurs.entity.Veterinarian;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeterinarianMapper {

    VeterinarianDto entityToDto(Veterinarian veterinarian);

    List<VeterinarianDto> entitiesToDtos(List<Veterinarian> veterinarians);

    @Mapping(target = "id", ignore = true)
    Veterinarian dtoToEntity(VeterinarianDto veterinarianDto);

    Veterinarian dtoToEntityWithId(VeterinarianDto veterinarianDto);
}
