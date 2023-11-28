package com.babinska.plannerfortutor.exception;

public class DownloadCsvException extends RuntimeException{

    public DownloadCsvException() {
        super("Wystąpił błąd podczas tworzenia pliku CSV");
    }
}
