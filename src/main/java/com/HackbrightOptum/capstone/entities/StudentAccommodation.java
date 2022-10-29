package com.HackbrightOptum.capstone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "Student_ID")
    private Student student;
    @ManyToOne
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
}
