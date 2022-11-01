package com.HackbrightOptum.capstone.entities;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Student_Accommodations")
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class StudentAccommodation {
    @Id
    @GeneratedValue
    @Column(name = "Student_Accommodation_ID")
    private Long studentAccommodationId;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Student_ID")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Accommodation_ID")
    private Accommodations accommodation;

    @Column(name = "Accommodation_Frequency")
    private int accommodationFrequency;

    @Column(name = "Accommodation_Received")
    private int accommodationReceived;

    public void addStudent(Student student){
        this.setStudent(student);
        student.getStudentAccommodationList().add(this);
    }

    public void addAccommodation(Accommodations accommodations){
        this.setAccommodation(accommodations);
        accommodations.getStudentAccommodationList().add(this);
    }

    public Accommodations getAccommodation() {
        return accommodation;
    }

    public StudentAccommodation(Student newStudent, Accommodations accommodations, StudentAccommodationDto studentAccommodationDto) {

        student = newStudent;
        accommodation = accommodations;

        if (accommodationFrequency != 0) {
            this.accommodationFrequency = studentAccommodationDto.getAccommodationFrequency();
        }
        if (accommodationReceived != 0) {
            this.accommodationReceived = studentAccommodationDto.getAccommodationReceived();
        }

//
//            this.studentAccommodationId = studentAccommodationDto.getAccommodationId();
//        }
//        if (accommodationFrequency != 0) {
//            this.accommodationFrequency = studentAccommodationDto.getAccommodationFrequency();
//        }
//        if (accommodationReceived != 0) {
//            this.accommodationReceived = studentAccommodationDto.getAccommodationReceived();
//        }

    }
//        public StudentAccommodation(Student student, Accommodations accommodations){
//        if(student != null){
//            this.student = student;
//        }
//        if(accommodations != null){
//            this.accommodation = accommodations.getAccommodationId();
//        }

    }
