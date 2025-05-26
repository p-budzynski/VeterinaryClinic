package pl.kurs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Patient;
import pl.kurs.validation.Create;
import pl.kurs.validation.Delete;
import pl.kurs.validation.Update;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class AnimalDto {
    @NotNull(message = "ID is required for update", groups = {Update.class, Delete.class})
    @Min(value = 1, message = "ID must be at least 1", groups = {Update.class, Delete.class})
    private Long id;

    @NotBlank(message = "Name must not be blank", groups = {Create.class, Update.class})
    private String name;

    @NotBlank(message = "Species must not be blank", groups = {Create.class, Update.class})
    private String species;

    @NotBlank(message = "Breed must not be blank", groups = {Create.class, Update.class})
    private String breed;

    @NotNull(message = "Age is required", groups = {Create.class, Update.class})
    @Min(value = 1, message = "Age must be greater than 0", groups = {Create.class, Update.class})
    @Max(value = 100, message = "Age must be less than 100", groups = {Create.class, Update.class})
    private Integer age;

    @NotBlank(message = "Patient must not be blank", groups = {Create.class, Update.class})
    private Patient patient;
}
