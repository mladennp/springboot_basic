package com.alibu.example.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = mapper.toStudent(dto);
        var savedStudent= repository.save(student);
        return mapper.toStudentresponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents(){
        return repository.findAll().
                stream().
                map(mapper::toStudentresponseDto).
                collect(Collectors.toList());


    }

    public StudentResponseDto findStudentById(Integer id) {
        return repository.findById(id).
                map(mapper::toStudentresponseDto).
                orElse(null);
    }

    public List<StudentResponseDto> findStudentsByName(String name) {
        return repository.findAllByNameContaining(name).stream().
                map(mapper::toStudentresponseDto).collect(Collectors.toList());
    }

    public void deleteStudent(Integer id){
        repository.deleteById(id);
    }




}
