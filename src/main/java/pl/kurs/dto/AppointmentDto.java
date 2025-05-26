package pl.kurs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Animal;
import pl.kurs.entity.Status;
import pl.kurs.entity.Veterinarian;
import pl.kurs.validation.Create;
import pl.kurs.validation.Delete;
import pl.kurs.validation.Update;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class AppointmentDto {
    @NotNull(message = "ID is required for update", groups = {Update.class, Delete.class})
    @Min(value = 1, message = "ID must be at least 1", groups = {Update.class, Delete.class})
    private Long id;

    @NotBlank(message = "Animal must not be blank", groups = {Create.class, Update.class})
    private Animal animal;

    @NotBlank(message = "Veterinarian must not be blank", groups = {Create.class, Update.class})
    private Veterinarian veterinarian;

    @NotBlank(message = "Date must not be blank", groups = {Create.class, Update.class})
    private LocalDateTime date;

    @NotBlank(message = "Description must not be blank", groups = {Create.class, Update.class})
    private String description;

    @NotBlank(message = "Status must not be blank", groups = {Create.class, Update.class})
    private Status status;

}
