package com.alibu.example.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto saveSchool(
            @RequestBody SchoolDto dto
    ){
        return schoolService.saveSchool(dto);
    }


    @GetMapping("/schools")
    public List<SchoolDto> findAll(
    ){
        return schoolService.findAllSchools();
    }



}
