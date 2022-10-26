package com.HackbrightOptum.capstone.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Accommodations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Accommodations {
    @Id
    @GeneratedValue
    @Column(name = "Accommodation_ID")
    private Long accommodationId;

    @Column(name = "Accommodation_Name")
    private String accommodationName;

    @Column(name = "Accommodation_Description")
    private String accommodationDescription;

    @ManyToMany(mappedBy = "accommodationsList")
    private List<Student> studentAccomList;

    @OneToMany(mappedBy = "accommodation")
    private List<StudentAccommodation> studentAccommodationList;
}
