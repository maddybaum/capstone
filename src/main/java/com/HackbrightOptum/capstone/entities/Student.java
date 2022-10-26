package com.HackbrightOptum.capstone.entities;

//import com.HackbrightOptum.capstone.DTOs.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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


    @ManyToMany
    @JoinTable(name = "Student_Course",
            joinColumns = { @JoinColumn(name = "Student_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Course_ID") }
    )
    private List<Course> courseList;


    @ManyToMany
    @JoinTable(name = "Student_Accommodations",
            joinColumns = { @JoinColumn(name = "Student_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Accommodation_ID") }
    )
    private List<Accommodations> accommodationsList;

    @OneToMany(mappedBy = "student")
    private List<StudentAccommodation> studentAccommodationList;

    //What do I need here?
//    public student(StudentDto studentDto) {
//        if (studentDto.getStudentName() != null) {
//            this.studentName = studentDto.getStudentName();
//        }
//    }
}