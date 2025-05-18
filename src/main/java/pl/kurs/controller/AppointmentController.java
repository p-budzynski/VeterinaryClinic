package pl.kurs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.AppointmentDto;
import pl.kurs.dto.AppointmentDtoList;
import pl.kurs.entity.Appointment;
import pl.kurs.mapper.AppointmentMapper;
import pl.kurs.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AppointmentDto> getById(@PathVariable("id") Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(a -> ResponseEntity.ok(appointmentMapper.entityToDto(a)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentDto> getAll() {
        List<Appointment> appointments = appointmentService.getAll();
        return appointmentMapper.entitiesToDtos(appointments);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public AppointmentDtoList getAllXml() {
        List<Appointment> appointments = appointmentService.getAll();
        List<AppointmentDto> dtos = appointmentMapper.entitiesToDtos(appointments);
        return new AppointmentDtoList(dtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = appointmentMapper.dtoToEntity(appointmentDto);
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return appointmentMapper.entityToDto(savedAppointment);
    }

    @PutMapping
    public AppointmentDto updateAppointment(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = appointmentMapper.dtoToEntityWithId(appointmentDto);
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        return appointmentMapper.entityToDto(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteAppointmentById(id);
    }
}
