package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement
@XmlSeeAlso({AnimalDto.class})
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDtoList {
    private List<AnimalDto> entities;

    @XmlAnyElement
    public List<AnimalDto> getEntities() {
        return entities;
    }
}
