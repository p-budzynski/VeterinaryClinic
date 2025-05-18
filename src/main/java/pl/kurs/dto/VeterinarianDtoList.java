package pl.kurs.dto;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement
@XmlSeeAlso({VeterinarianDto.class})
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianDtoList {
    private List<VeterinarianDto> entities;

    @XmlAnyElement
    public List<VeterinarianDto> getEntities() {
        return entities;
    }
}
