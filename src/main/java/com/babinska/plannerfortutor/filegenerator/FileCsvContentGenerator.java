package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Service
class FileCsvContentGenerator implements FileContentGenerator {
    @Override
    public byte[] generateFile(List<StudentsDownloadsView> inputData) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {

            CSVWriter csvWriter = new CSVWriter(outputStreamWriter);
            String[] headers = Arrays.stream(StudentsDownloadsView.class.getDeclaredMethods())
                    .map(Method::getName)
                    .map(s -> s.substring(3))
                    .toArray(String[]::new);
            csvWriter.writeNext(headers, false);

            for (StudentsDownloadsView inputDatum : inputData) {
                String[] line = {inputDatum.getFirstname(), inputDatum.getLastname(), inputDatum.getEmail(), inputDatum.getPhoneNumber(), inputDatum.getSchoolClass()};
                csvWriter.writeNext(line, false);
            }

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supportsFormat(FileFormat fileFormat) {
        return fileFormat.equals(FileFormat.CSV);
    }
}
