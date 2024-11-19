package com.alibu.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void should_save_student() {
        StudentDto dto = new StudentDto("mladen", "mijailovic",
                    "mijailovicmladen5", 1);

        Student student = new Student("mladen", "mijailovic",
                "mijailovicmladen5", 23);

        Student savedStudent = new Student("mladen", "mijailovic", "mijailovicmladen5",
                23);

        savedStudent.setId(1);

        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentresponseDto(savedStudent)).thenReturn(new StudentResponseDto(
                "mladen", "mijailovic", "mijailovicmladen5"
        ));



    StudentResponseDto studentResponseDto = studentService.saveStudent(dto);
    assertEquals(studentResponseDto.name(), dto.name());
    assertEquals(studentResponseDto.lastName(), dto.lastName());
    assertEquals(studentResponseDto.email(), dto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentresponseDto(savedStudent);
    }

    @Test
    public void allStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("mladen", "mijailovic",
                "mijailovicmladen5", 23));

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentresponseDto(any(Student.class))).thenReturn(new StudentResponseDto(
                "mladen", "mijailovic",
                "mijailovicmladen5"));


        List<StudentResponseDto> responseDtos = studentService.findAllStudents();
        assertEquals(responseDtos.size(), students.size());
        verify(studentRepository, times(1)).findAll();



    }

}