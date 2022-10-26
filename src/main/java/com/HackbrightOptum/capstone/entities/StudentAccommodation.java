package com.HackbrightOptum.capstone.entities;

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


}
