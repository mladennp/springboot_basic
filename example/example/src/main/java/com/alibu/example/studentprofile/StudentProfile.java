package com.alibu.example.studentprofile;

import com.alibu.example.student.Student;
import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private int id;
    private String studentBio;
    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    public StudentProfile() {}
    public StudentProfile(String studentBio) {
        this.studentBio = studentBio;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }
    public String getStudentBio() {
        return studentBio;
    }
    public void setStudentBio(String studentBio) {
        this.studentBio = studentBio;
    }

    public void setId(int id) {
        this.id = id;
    }

}
