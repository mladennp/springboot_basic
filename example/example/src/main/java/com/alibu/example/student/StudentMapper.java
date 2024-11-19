package com.alibu.example.student;

import com.alibu.example.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponseDto toStudentresponseDto(Student student){
        return new StudentResponseDto(student.getName(), student.getLastName(), student.getEmail());
    }
    public Student toStudent(StudentDto dto){
        if(dto==null){
            throw new NullPointerException("dto is null");
        }
        var student = new Student();
        student.setEmail(dto.email());
        student.setName(dto.name());
        student.setLastName(dto.lastName());
        var school = new School();
        school.setId(dto.school_id());
        student.setSchool(school);
        return student;
    }



}
