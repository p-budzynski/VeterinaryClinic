package pl.kurs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Specialization;
import pl.kurs.validation.Create;
import pl.kurs.validation.Delete;
import pl.kurs.validation.Update;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class VeterinarianDto {
    @NotNull(message = "ID is required for update", groups = {Update.class, Delete.class})
    @Min(value = 1, message = "ID must be at least 1", groups = {Update.class, Delete.class})
    private Long id;

    @NotBlank(message = "First name must not be blank", groups = {Create.class, Update.class})
    private String firstName;

    @NotBlank(message = "Last name must not be blank", groups = {Create.class, Update.class})
    private String lastName;

    @NotBlank(message = "Specialization must not be blank", groups = {Create.class, Update.class})
    private Specialization specialization;

    @NotBlank(message = "E-mail must not be blank", groups = {Create.class, Update.class})
    private String email;

    @NotBlank(message = "Phone number must not be blank", groups = {Create.class, Update.class})
    private String phoneNumber;
}
