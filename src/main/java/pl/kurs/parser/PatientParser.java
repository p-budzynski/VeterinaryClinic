package pl.kurs.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.dto.PatientDto;
import pl.kurs.exception.CsvValidationException;
import pl.kurs.validation.Create;

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

    public List<PatientDto> parseCsv(MultipartFile file) throws IOException {
        List<PatientDto> patientDtos = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<PatientDto> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(PatientDto.class);

            CsvToBean<PatientDto> csvToBean = new CsvToBeanBuilder<PatientDto>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(false)
                    .build();

            int rowNumber = 1;
            for (PatientDto csvLine : csvToBean) {
                Set<ConstraintViolation<PatientDto>> violations = validator.validate(csvLine, Create.class);

                if (!violations.isEmpty()) {
                    for (ConstraintViolation<PatientDto> violation : violations) {
                        errors.add("Row " + rowNumber + ": " + violation.getMessage());
                    }
                } else {
                    patientDtos.add(csvLine);
                }
                rowNumber++;
            }
        }
        if (!errors.isEmpty()) {
            throw new CsvValidationException("CSV validation failed. " + errors);
        }

        return patientDtos;
    }

}
