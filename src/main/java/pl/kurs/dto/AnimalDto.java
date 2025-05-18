package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.entity.Patient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class AnimalDto {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private Patient patient;
}
