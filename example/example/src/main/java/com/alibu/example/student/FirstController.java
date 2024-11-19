package com.alibu.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class FirstController {



    private final StudentService studentService;

    public FirstController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/post")
    public String post(
          @RequestBody String message
    ){
        return "Post successful and message is "+ message;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid @RequestBody StudentDto dto
    ){
        return this.studentService.saveStudent(dto);
    }


    @GetMapping("/students/{students-id}")
    public StudentResponseDto studentId(
            @PathVariable("students-id") Integer id
    ) {
        return studentService.findStudentById(id);

    }

    @GetMapping("/studentsfind")
    public List<StudentResponseDto> findStudents(){
        return this.studentService.findAllStudents();

    }

    @GetMapping("/students/search/{students-name}")
    public List<StudentResponseDto> searchStudents(
            @PathVariable("students-name") String name
    ) {
        return this.studentService.findStudentsByName(name);

    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(
            @PathVariable("student-id") Integer studentId
    ){
        this.studentService.deleteStudent(studentId);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exception){

        var errors= new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            var fieldName= ((FieldError) error).getField();
            var errorMessage= error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    }
