package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.Teacher;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CourseDto implements Serializable {
    private Long courseId;
    private String courseName;
    private Teacher teacher;
    private int numberOfCoursesElapsed;
    private List<StudentDto> courseStudentList = new ArrayList<>();
    //private CourseDto courseDto;

    public CourseDto(Course course){
        if(course.getCourseId() != null){
            this.courseId = course.getCourseId();
        }
        if(course.getCourseName() != null){
            this.courseName = course.getCourseName();
        }
        if(course.getNumberOfCoursesElapsed() != 0){
            this.numberOfCoursesElapsed = course.getNumberOfCoursesElapsed();
        }
        if(course.getTeacher() != null){
            this.teacher = course.getTeacher();
        }
        }


    }

