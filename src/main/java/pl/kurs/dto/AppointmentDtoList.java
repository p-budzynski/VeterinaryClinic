package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement
@XmlSeeAlso({AppointmentDto.class})
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDtoList {
    private List<AppointmentDto> entities;

    @XmlAnyElement
    public List<AppointmentDto> getEntities() {
        return entities;
    }
}
