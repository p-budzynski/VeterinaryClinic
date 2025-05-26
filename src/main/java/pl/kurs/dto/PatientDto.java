package pl.kurs.dto;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.validation.Create;
import pl.kurs.validation.Delete;
import pl.kurs.validation.Update;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class PatientDto {
    @NotNull(message = "ID is required for update", groups = {Update.class, Delete.class})
    @Min(value = 1, message = "ID must be at least 1", groups = {Update.class, Delete.class})
    private Long id;

    @CsvBindByName(column = "firstname")
    @NotBlank(message = "First name must not be blank", groups = {Create.class, Update.class})
    private String firstName;

    @CsvBindByName(column = "lastname")
    @NotBlank(message = "Last name must not be blank", groups = {Create.class, Update.class})
    private String lastName;

    @CsvBindByName(column = "phonenumber")
    @NotBlank(message = "Phone number must not be blank", groups = {Create.class, Update.class})
    private String phoneNumber;

    @CsvBindByName(column = "email")
    @NotBlank(message = "E-mail must not be blank", groups = {Create.class, Update.class})
    private String email;

    @CsvBindByName(column = "address")
    @NotBlank(message = "Address must not be blank", groups = {Create.class, Update.class})
    private String address;

    public PatientDto(String firstName, String lastName, String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
