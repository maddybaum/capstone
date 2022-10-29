package com.HackbrightOptum.capstone.entities;

//import com.HackbrightOptum.capstone.DTOs.StudentDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {
    @Id
    @GeneratedValue
    @Column(name = "Student_ID")
    private Long studentId;

    @Column(name = "Student_Name")
    private String studentName;

    public Student(StudentDto studentDto) {
    }


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

//    @ManyToMany
//    @JoinTable(name = "Student_Accommodations",
//            joinColumns = { @JoinColumn(name = "Student_ID") },
//            inverseJoinColumns = { @JoinColumn(name = "Accommodation_ID") }
//    )
//    private List<Accommodations> accommodationsList;

    public List<StudentAccommodation> getStudentAccommodationList() {
        if(studentAccommodationList == null){
            studentAccommodationList = new ArrayList<>();
        }
        return studentAccommodationList;
    }
    /*
     *  this is a convenient utility kind of method to add the relationship between
     *  this accommmodation and this input student
     */
    public void addStudentAccommodation(StudentAccommodation studentAccommodation) {
        studentAccommodation.setStudent(this);
        this.getStudentAccommodationList().add(studentAccommodation);
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAccommodation> studentAccommodationList;

    //What do I need here?
//    public student(StudentDto studentDto) {
//        if (studentDto.getStudentName() != null) {
//            this.studentName = studentDto.getStudentName();
//        }
//    }
}