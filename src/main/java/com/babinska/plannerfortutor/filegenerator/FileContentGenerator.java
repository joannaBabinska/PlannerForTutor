package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;

import java.util.List;

public interface FileContentGenerator {

    byte[] generateFile(List<StudentsDownloadsView> inputData);

    boolean supportsFormat(FileFormat fileFormat);
}
