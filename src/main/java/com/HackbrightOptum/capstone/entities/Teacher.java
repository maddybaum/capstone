package com.HackbrightOptum.capstone.entities;

//import com.HackbrightOptum.capstone.DTOs.TeacherDto;
import com.HackbrightOptum.capstone.dtos.TeacherDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Teacher_ID")
    private Long teacherId;

    @Column(name = "Teacher_Name", unique = true)
    private String teacherName;

    @Column(name = "Teacher_Password")
    private String teacherPassword;


    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Course> teacherCourses = new HashSet<>();


    public Teacher(TeacherDto teacherDto) {
        if (teacherDto.getTeacherName() != null) {
            this.teacherName = teacherDto.getTeacherName();
        }
        if (teacherDto.getTeacherPassword() != null) {
            this.teacherPassword = teacherDto.getTeacherPassword();
        }

    }
}

