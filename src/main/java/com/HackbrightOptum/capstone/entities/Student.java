package com.HackbrightOptum.capstone.entities;

//import com.HackbrightOptum.capstone.DTOs.StudentDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Students")
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Student {
    @Id
    @GeneratedValue
    @Column(name = "Student_ID")
    private Long studentId;

    @Column(name = "Student_Name")
    private String studentName;




    public List<Course> getCourseList() {
        if(courseList == null){
            courseList = new ArrayList<>();
        }
        return courseList;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Student_Course",
            joinColumns = { @JoinColumn(name = "Student_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Course_ID") }
    )
    private List<Course> courseList;

    public void addCourse(Course course){
        courseList.add(course);
        course.getStudentList().add(this);
    }

    public List<StudentAccommodation> getStudentAccommodationList() {
        if(studentAccommodationList == null){
            this.studentAccommodationList = new ArrayList<>();
        }
        return studentAccommodationList;
    }

    public void addStudentAccommodation(StudentAccommodation studentAccommodation) {
        studentAccommodation.setStudent(this);
        this.getStudentAccommodationList().add(studentAccommodation);
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE)
    private List<StudentAccommodation> studentAccommodationList;

    //What do I need here?
    public Student(StudentDto studentDto) {
        if(studentDto.getStudentId() != null){
            this.studentId = studentDto.getStudentId();
        }
        if (studentDto.getStudentName() != null) {
            this.studentName = studentDto.getStudentName();
        }
//        if(studentDto.getStudentAccommodationList() != null){
//            this.studentAccommodationList = studentDto.getStudentAccommodationList();
////        }
    }

}