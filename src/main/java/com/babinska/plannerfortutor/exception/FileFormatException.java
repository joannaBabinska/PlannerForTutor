package com.babinska.plannerfortutor.exception;

public class FileFormatException extends RuntimeException {
    public FileFormatException(String fileFormat) {
        super(String.format("Nie można wygenerować pliku w formacie %s", fileFormat));
    }
}
