package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.aspect.TrackExecutionTime;
import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.student.dto.StudentDownloadFile;
import com.babinska.plannerfortutor.student.dto.StudentDto;
import com.babinska.plannerfortutor.student.dto.StudentRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@TrackExecutionTime
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {

  private final StudentService studentService;
  private final StudentCsvService studentCsvService;
  private final StudentFilesGenerator studentFilesGenerator;

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping
  public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentRegistrationDto studentRegistrationDto) {
    StudentDto savedStudentDto = studentService.addStudent(studentRegistrationDto);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedStudentDto.id())
            .toUri();

    log.info("Add new student: {}", savedStudentDto);
    return ResponseEntity.created(uri).body(savedStudentDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> replaceStudent(@PathVariable Long id,@Valid @RequestBody StudentRegistrationDto studentRegistrationDto) {
    StudentDto studentDto = studentService.replaceStudent(studentRegistrationDto, id);
    log.info("Replace student with id = {} to student: {}", id, studentDto);
    return ResponseEntity.ok(studentDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@Valid @RequestBody JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    StudentDto savedStudentDto = studentService.updateStudent(id, jsonMergePatch);
    log.info("Updated student with id ={}", id);
    return ResponseEntity.ok(savedStudentDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/files/upload/csv/single",consumes = {"multipart/form-data"})
  public ResponseEntity<Void> uploadStudentCsvFile(@RequestParam("file") MultipartFile file) {
    studentCsvService.uploadFile(file);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/files/upload/csv", consumes = {"multipart/form-data"})
  public ResponseEntity<Void> uploadStudentCsvFiles(@RequestParam("files") MultipartFile[] files) {
    studentCsvService.uploadFiles(files);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/files/download")
  public ResponseEntity<byte[]> downloadFile(
      @RequestParam(value = "fileFormat") FileFormat fileFormat,
      @RequestParam(value = "sort", defaultValue = "id,asc") String[] sort) {
    StudentDownloadFile file = studentFilesGenerator.generateFile(fileFormat, null); // TODO create utility class; eg. SortUtils.createFrom(sort);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", file.name());
    return new ResponseEntity<>(file.content(), headers, HttpStatus.OK);
  }

}
