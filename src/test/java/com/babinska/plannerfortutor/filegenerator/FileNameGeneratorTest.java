package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.util.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileNameGeneratorTest {

    FileNameGenerator fileNameGenerator;

    DateProvider dateProvider;

    @BeforeEach
    void beforeEach() {
        dateProvider = Mockito.mock(DateProvider.class);
        fileNameGenerator = new FileNameGenerator(dateProvider);
    }


    @Test
    void shouldGenerateNameFor2023_11_26() {
//        given:
        Mockito.when(dateProvider.now()).thenReturn(LocalDate.of(2023, 1, 26));
//        when:
        String generate = fileNameGenerator.generate(FileFormat.CSV);
//        then:
        assertEquals("Test - 26-01-2023.csv", generate);
    }

}