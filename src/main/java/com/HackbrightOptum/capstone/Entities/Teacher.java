package com.HackbrightOptum.capstone.Entities;

import com.HackbrightOptum.capstone.DTOs.TeacherDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Teachers")


public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String teacherName;

    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    //@JoinColumn(name = "classId", nullable = false)
    private Course teacherCourse;

    public Teacher() {
    }

    public Teacher(TeacherDto teacherDto){
        if(teacherDto.getUsername() != null){
            this.username = teacherDto.getUsername();
        }
        if(teacherDto.getPassword() != null){
            this.password = teacherDto.getPassword();
        }
        if(teacherDto.getTeacherName() != null){
            this.teacherName = teacherDto.getTeacherName();
        }
        if(teacherDto.getTeacherCourse() != null){
            this.teacherCourse = teacherDto.getTeacherCourse();
        }
    }
    public Teacher(Long teacherId, String username, String password, String teacherName, Course teacherCourse) {
        this.teacherId = teacherId;
        this.username = username;
        this.password = password;
        this.teacherName = teacherName;
        this.teacherCourse = teacherCourse;
    }

    public Course getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(Course teacherCourse) {
        this.teacherCourse = teacherCourse;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
