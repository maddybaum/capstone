package com.HackbrightOptum.capstone.entities;


//import com.HackbrightOptum.capstone.DTOs.CourseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "Course_ID")
    private Long courseId;

    @Column(name = "Course_Name")
    private String courseName;

    @Column(name = "Number_Of_Courses_Elapsed")
    private int numberOfCoursesElapsed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Teacher teacher;

    //private Set<Student> courseRoster;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList;

//    public Course(CourseDto courseDto) {
//        if (courseDto.getCourseId() != null) {
//            this.courseId = courseDto.getCourseId();
//        }
//        if (courseDto.getCourseName() != null) {
//            this.courseName = courseDto.getCourseName();
//        }
//    }
}

