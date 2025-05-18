package pl.kurs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kurs.dto.PatientDto;
import pl.kurs.entity.Patient;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto entityToDto(Patient patient);

    List<PatientDto> entitiesToDtos(List<Patient> patients);

    @Mapping(target = "id", ignore = true)
    Patient dtoToEntity(PatientDto patientDto);

    Patient dtoToEntityWithId(PatientDto patientDto);
}
