package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Animal;
import pl.kurs.entity.Status;
import pl.kurs.entity.Veterinarian;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class AppointmentDto {
    private Long id;
    private Animal animal;
    private Veterinarian veterinarian;
    private LocalDateTime date;
    private String description;
    private Status status;
}
