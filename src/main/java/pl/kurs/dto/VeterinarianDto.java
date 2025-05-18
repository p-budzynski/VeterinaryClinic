package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Specialization;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class VeterinarianDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Specialization specialization;
    private String email;
    private String phoneNumber;
}
