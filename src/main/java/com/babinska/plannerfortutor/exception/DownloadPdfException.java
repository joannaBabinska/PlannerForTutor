package com.babinska.plannerfortutor.exception;

public class DownloadPdfException extends RuntimeException{
    public DownloadPdfException() {
        super("Błąd podczas pobierania pliku PDF");
    }
}
