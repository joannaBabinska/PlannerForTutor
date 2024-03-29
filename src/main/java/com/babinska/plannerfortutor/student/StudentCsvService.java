package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.aspect.TrackExecutionTime;
import com.babinska.plannerfortutor.exception.UploadFileExceptions;
import com.babinska.plannerfortutor.student.dto.StudentCsvRepresentation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@TrackExecutionTime
class StudentCsvService {

    private final StudentRepository studentRepository;

    public void uploadFile(final MultipartFile file) {
        Set<Student> students = parseCsv(file);
        studentRepository.saveAll(students);
    }

    public void uploadFiles(final MultipartFile[] files) {
        Set<Student> students = Stream.of(files).map(this::parseCsv)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        studentRepository.saveAll(students);
    }

    private Set<Student> parseCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<StudentCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCsvRepresentation.class);
            CsvToBean<StudentCsvRepresentation> csvToBean = new CsvToBeanBuilder<StudentCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine ->
                        Student.builder()
                            .firstName(csvLine.getFirstName())
                            .lastName(csvLine.getLastName())
                            .email(csvLine.getEmail())
                            .dateOfBirth(csvLine.getDateOfBirth())
                            .phoneNumber(csvLine.getPhoneNumber())
                            .schoolClass(csvLine.getSchoolClass())
                            .build()
                    )
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new UploadFileExceptions(file.getName());
        }
    }

}
