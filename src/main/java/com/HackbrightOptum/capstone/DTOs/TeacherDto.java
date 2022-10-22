package com.HackbrightOptum.capstone.DTOs;

import com.HackbrightOptum.capstone.Entities.Course;
import com.HackbrightOptum.capstone.Entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TeacherDto implements Serializable {
    private Long id;
    private String username;
    private String password;

    private String teacherName;

    private Course teacherCourse;

    private Set<StudentDto> studentDtoSet = new HashSet<>();

    public TeacherDto(Teacher teacher){
        if(teacher.getTeacherId() != null){
            this.id = teacher.getTeacherId();
        }
        if(teacher.getUsername() != null){
            this.username = teacher.getUsername();
        }
        if(teacher.getPassword() != null){
            this.password = teacher.getPassword();
        }
        if(teacher.getTeacherName() != null){
            this.teacherName = teacher.getTeacherName();
        }
        if(teacher.getTeacherCourse() != null){
            this.teacherCourse = teacher.getTeacherCourse();
        }
    }
}
