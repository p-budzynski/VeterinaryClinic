package pl.kurs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.kurs.dto.AppointmentDto;
import pl.kurs.entity.Appointment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentDto entityToDto(Appointment appointment);

    List<AppointmentDto> entitiesToDtos(List<Appointment> appointments);

    @Mapping(target = "id", ignore = true)
    Appointment dtoToEntity(AppointmentDto appointmentDto);

    Appointment dtoToEntityWithId(AppointmentDto appointmentDto);
}
