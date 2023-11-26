package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.util.DateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FileNameGenerator {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateProvider dateProvider;

    public String generate(FileFormat fileFormat) {
        return "Test - %s.%s".formatted(dateProvider.now().format(FORMAT),fileFormat.name().toLowerCase());
    }
}
