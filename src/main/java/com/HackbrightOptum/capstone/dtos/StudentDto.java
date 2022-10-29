package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
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
public class StudentDto implements Serializable {
    private Long studentId;
    private String studentName;

//    private List<Course> studentCourses = new ArrayList<>();
    private Course studentCourse;

    private Accommodations studentAccommodation;

    private int accommodationFrequency;

    private int accommodationReceived;
//    public List<Accommodations> getStudentAccommodations() {
//        if(studentAccommodations == null){
//            studentAccommodations = new ArrayList<>();
//        }
//        return studentAccommodations;
//    }

//    private List<Accommodations> studentAccommodations = new ArrayList<>();
    //Do I need below?
    //private StudentDto studentDto;
    public StudentDto(Student student) {
        if (student.getStudentId() != null) {
            this.studentId = student.getStudentId();
        }
        if (student.getStudentName() != null) {
            this.studentName = student.getStudentName();
        }

    }

    public int getAccommodationFrequency() {
        return accommodationFrequency;
    }

    public int getAccommodationReceived() {
        return accommodationReceived;
    }
}
