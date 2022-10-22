package com.HackbrightOptum.capstone.DTOs;

import com.HackbrightOptum.capstone.Entities.Course;
import com.HackbrightOptum.capstone.Entities.Student;
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

public class CourseDto implements Serializable {
    private Long courseId;
    private String courseName;
    private Teacher courseTeacher;
    //Does the set need to be of StudentDtos? If so how do I get that to work on teh courseServiceImpl to update the students?
    private Set<Student> courseStudentSet = new HashSet<>();

    public CourseDto(Course course){
        if(course.getCourseId() != null){
            this.courseId = course.getCourseId();
        }
        if(course.getCourseName() != null){
            this.courseName = course.getCourseName();
        }
        if(course.getCourseTeacher() != null){
            this.courseTeacher = course.getCourseTeacher();

        }
    }
}
