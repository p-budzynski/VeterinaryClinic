package pl.kurs.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.kurs.dto.PatientDto;
import pl.kurs.entity.Patient;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto entityToDto(Patient patient);

    List<PatientDto> entitiesToDtos(List<Patient> patients);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    @Mapping(target = "id", ignore = true)
    List<Patient> dtosToEntities(List<PatientDto> patientDtos);

    @Named("dtoToEntity")
    @Mapping(target = "id", ignore = true)
    Patient dtoToEntity(PatientDto patientDto);

    @Named("dtoToEntityWithId")
    Patient dtoToEntityWithId(PatientDto patientDto);
}
