package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.student.dto.StudentCsvDownloadFile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
class StudentCsvService {

  public void uploadFile(final MultipartFile file) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  public void uploadFiles(final MultipartFile[] files) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  public StudentCsvDownloadFile generateFile(Sort sort) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

}
