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
    private String studentName;
    private Long studentAccommodationId;

    private Long accommodationId;

    private String accommodationName;

    private String accommodationDescription;
    private int accommodationFrequency;
    private int accommodationReceived;
    private int coursesElapsed;

    public StudentAccommodationDto(StudentAccommodation studentAccommodation) {
        if (studentAccommodation.getStudentAccommodationId() != null) {
            this.studentId = studentAccommodation.getStudentAccommodationId();
        }
        if (studentAccommodation.getStudentAccommodationId() != null) {
            this.studentAccommodationId = studentAccommodation.getStudentAccommodationId();
        }
        if (studentAccommodation.getStudent() != null) {
            this.accommodationFrequency = studentAccommodation.getAccommodationFrequency();
        }
        if (studentAccommodation.getAccommodation() != null) {
            this.accommodationReceived = studentAccommodation.getAccommodationReceived();
       }
        if (studentAccommodation.getAccommodation() != null) {
            this.accommodationId = studentAccommodation.getStudentAccommodationId();
            }
        if(studentAccommodation.getAccommodation().getAccommodationDescription() != null){
            this.accommodationDescription = studentAccommodation.getAccommodation().getAccommodationDescription();
        }
        if(studentAccommodation.getStudent().getStudentName() != null){
            this.studentName = studentAccommodation.getStudent().getStudentName();
        }
        if(studentAccommodation.getAccommodation().getAccommodationName() != null){
            this.accommodationName = studentAccommodation.getAccommodation().getAccommodationName();
        }
    }}
