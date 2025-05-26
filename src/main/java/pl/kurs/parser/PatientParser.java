package pl.kurs.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.dto.PatientDto;
import pl.kurs.entity.Patient;
import pl.kurs.mapper.PatientMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientParser {
    private final Validator validator;
    private final PatientMapper patientMapper;

    public List<Patient> parseCsv(MultipartFile file) throws IOException {
        List<PatientDto> patientDtos = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<PatientDto> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(PatientDto.class);

            CsvToBean<PatientDto> csvToBean = new CsvToBeanBuilder<PatientDto>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(false)
                    .build();

            for (PatientDto csvLine : csvToBean) {
                Set<ConstraintViolation<PatientDto>> violations = validator.validate(csvLine);

                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException("Validation failed for CSV row: " + csvLine, violations);
                }

                patientDtos.add(csvLine);
            }
        }

        return patientMapper.dtosToEntities(patientDtos);
    }

}
