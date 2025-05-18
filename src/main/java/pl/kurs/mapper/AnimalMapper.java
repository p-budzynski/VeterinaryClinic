package pl.kurs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kurs.dto.AnimalDto;
import pl.kurs.entity.Animal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDto entityToDto(Animal animal);

    List<AnimalDto> entitiesToDtos(List<Animal> animals);

    @Mapping(target = "id", ignore = true)
    Animal dtoToEntity(AnimalDto animalDto);

    Animal dtoToEntityWithId(AnimalDto animalDto);

}
