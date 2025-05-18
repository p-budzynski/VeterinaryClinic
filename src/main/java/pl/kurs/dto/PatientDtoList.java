package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement
@XmlSeeAlso({PatientDto.class})
@NoArgsConstructor
@AllArgsConstructor
public class PatientDtoList {
    private List<PatientDto> entities;

    @XmlAnyElement
    public List<PatientDto> getEntities() {
        return entities;
    }
}
