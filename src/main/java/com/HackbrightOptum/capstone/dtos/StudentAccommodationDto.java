package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAccommodationDto implements Serializable {
    private Long studentId;
    private int accommodationFrequency;
    private int accommodationReceived;

//    public StudentAccommodationDto(StudentAccommodation studentAccommodation){
//        if(studentAccommodation.getStudentAccommodationId() != null){
//            this.studentAccommodationId = studentAccommodation.getStudentAccommodationId();
//        }
//        if(studentAccommodation.getStudent() != null){
//            this.studentAccommodationStudent = studentAccommodation.getStudent();
//        }
//        if(studentAccommodation.getAccommodation() != null){
//            this.studentAccommodationAccommodation = studentAccommodation.getAccommodation();
//        }
//    }
}
