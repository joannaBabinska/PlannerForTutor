package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;

import java.util.List;

class FilePdfContentGenerator implements FileContentGenerator {
    @Override
    public byte[] generateFile(List<StudentsDownloadsView> inputData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supportsFormat(FileFormat fileFormat) {
        return fileFormat.equals(FileFormat.PDF);
    }
}
