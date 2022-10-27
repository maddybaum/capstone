package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CourseDto implements Serializable {
    private Long courseId;
    private String courseName;
    private String courseTeacher;
    private int numberOfCoursesElapsed;
    private Set<StudentDto> courseStudentSet = new HashSet<>();
    //private CourseDto courseDto;

    public CourseDto(Course course){
        if(course.getCourseId() != null){
            this.courseId = course.getCourseId();
        }
        if(course.getCourseName() != null){
            this.courseName = course.getCourseName();
        }
    }
}
