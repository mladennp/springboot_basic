package com.alibu.example.student;

import com.alibu.example.school.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    public void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtotoStudent() {
        StudentDto dto= new StudentDto(
                "Mladen", "Mijailovic",
                "mijailovicmladen5@gmail.com", 1
        );
        Student student = studentMapper.toStudent(dto);

        assertEquals(dto.name(), student.getName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.school_id(), student.getSchool().getId());



    }

    @Test
    public void should_throw_NullPointerException(){
        var exp=assertThrows(NullPointerException.class, ()-> studentMapper.toStudent(null));
        assertEquals(exp.getMessage(), "dto is null");
    }


    @Test
    public void shouldMapStudentToStudentDto() {
        Student student= new Student("Mladen", "Mijailovic",
                "mijailovicmladen5@gmail.com", 23);
        School school= new School("Mesa Selimovic");
        student.setSchool(school);

        StudentResponseDto dto= studentMapper.toStudentresponseDto(student);
        assertEquals(dto.name(), student.getName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());

    }


}