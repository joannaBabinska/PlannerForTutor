package com.babinska.plannerfortutor.exception;

public class UploadFileExceptions extends RuntimeException {
    public UploadFileExceptions(String name) {
        super(String.format("Can not upload file \"%s\"", name));
    }
}