package com.HackbrightOptum.capstone.dtos;

//import com.HackbrightOptum.capstone.entities.Accommodations;
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

    private Long accommodationId;
    private int accommodationFrequency;
    private int accommodationReceived;


    public StudentAccommodationDto(StudentAccommodation studentAccommodation){
        if(studentAccommodation.getStudentAccommodationId() != null){
            this.studentId = studentAccommodation.getStudentAccommodationId();
        }
        if(studentAccommodation.getStudentAccommodationId() != null){
            this.accommodationId = studentAccommodation.getStudentAccommodationId();
        }
        if(studentAccommodation.getStudent() != null){
            this.accommodationFrequency = studentAccommodation.getAccommodationFrequency();
        }
//        if(studentAccommodation.getAccommodation() != null){
//            this.accommodationReceived = studentAccommodation.getAccommodationReceived();
//        }
    }
}
