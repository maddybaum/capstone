package com.HackbrightOptum.capstone.entities;


//import com.HackbrightOptum.capstone.DTOs.CourseDto;
import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "Course_ID")
    private Long courseId;

    @Column(name = "Course_Name")
    private String courseName;

    @Column(name = "Number_Of_Courses_Elapsed")
    private int numberOfCoursesElapsed;

    //Changed CascadeType.Merge to CascadeType.All
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Teacher teacher;

    //private Set<Student> courseRoster;

//    public List<StudentDto> getStudentList() {
//        if(studentList == null){
//            studentList = new ArrayList<>();
//        }
//        return studentList;
//    }
    @JsonIgnore
    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> studentList;

    /*
     *  this is a convenient utility kind of method to add the relationship between
     *  this course and this input student
     */
    public void addStudent(Student student) {
        this.getStudentList().add(student);
        student.getCourseList().add(this);
    }

    public Course(CourseDto courseDto, Teacher newTeacher) {
        teacher = newTeacher;
        if (courseDto.getCourseId() != null) {
            this.courseId = courseDto.getCourseId();
        }
        if (courseDto.getCourseName() != null) {
            this.courseName = courseDto.getCourseName();
        }
        if(courseDto.getNumberOfCoursesElapsed() != 0){
            this.numberOfCoursesElapsed = courseDto.getNumberOfCoursesElapsed();
        }
//        if(courseDto.getCourseStudentList() != null){
//            this.studentList = courseDto.getCourseStudentList();
//        }
    }
}

