package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.exception.FileFormatException;
import com.babinska.plannerfortutor.filegenerator.FileContentGenerator;
import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.filegenerator.FileNameGenerator;
import com.babinska.plannerfortutor.student.dto.StudentDownloadFile;
import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class StudentFilesGenerator {

  private final List<FileContentGenerator> fileContentGenerators;
  private final StudentRepository studentRepository;
  private final FileNameGenerator fileNameGenerator;

  public StudentDownloadFile generateFile(FileFormat fileFormat, Sort sort) {
    List<StudentsDownloadsView> students = getStudentToDownloadFile();
students.get(0).getPhoneNumber();
    byte[] content = fileContentGenerators.stream()
            .filter(fileContentGenerator -> fileContentGenerator.supportsFormat(fileFormat))
            .findFirst()
            .orElseThrow(() -> new FileFormatException(fileFormat.toString()))
            .generateFile(students);

    String fileName = fileNameGenerator.generate(fileFormat);
    return new StudentDownloadFile(fileName, content);
  }

  private List<StudentsDownloadsView> getStudentToDownloadFile() {
    return studentRepository.getAllStudentsDownloads();
  }

}
