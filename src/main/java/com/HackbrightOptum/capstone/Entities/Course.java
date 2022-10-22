package com.HackbrightOptum.capstone.Entities;


import com.HackbrightOptum.capstone.DTOs.CourseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Course")

public class Course {
   @Id
   @GeneratedValue
    private Long courseId;

   @Column
   private String courseName;


   @OneToOne
    @JsonBackReference
    private Teacher courseTeacher;

   @OneToMany(mappedBy = "newCourse", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Student> courseStudents;

    public Course(Long courseId, String courseName, Teacher courseTeacher, Set<Student> courseStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseStudents = courseStudents;
    }

    public Course() {
    }

    public Course(CourseDto courseDto) {
        if (courseDto.getCourseId() != null) {
            this.courseId = courseDto.getCourseId();
        }
        if (courseDto.getCourseName() != null) {
            this.courseName = courseDto.getCourseName();
        }
        if (courseDto.getCourseTeacher() != null) {
            this.courseTeacher = courseDto.getCourseTeacher();
        }
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(Teacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public Set<Student> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<Student> courseStudents) {
        this.courseStudents = courseStudents;
    }
}
