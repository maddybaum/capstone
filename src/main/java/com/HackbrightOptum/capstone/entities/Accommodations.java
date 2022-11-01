package com.HackbrightOptum.capstone.entities;

import com.HackbrightOptum.capstone.dtos.AccommodationsDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    //@ManyToMany(mappedBy = "accommodationsList")
    //private List<Student> studentAccomList;

//    public List<StudentAccommodation> getStudentAccommodationList() {
//        if(getStudentAccommodationList() != null){
//            this.studentAccommodationList = new ArrayList<>();
//        }
//        return studentAccommodationList;
//    }

    public void addStudentAccommodation(StudentAccommodation studentAccommodation) {
        studentAccommodation.setAccommodation(this);
        this.getStudentAccommodationList().add(studentAccommodation);
    }
    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
//@OneToMany(mappedBy = "accommodation")
    private List<StudentAccommodation> studentAccommodationList;

    public Accommodations(Accommodations accommodations){
        if(accommodations.getAccommodationId() != null){
            this.accommodationId = accommodations.getAccommodationId();
        }
        if(accommodations.getAccommodationName() != null){
            this.accommodationName = accommodations.getAccommodationName();
        }
        if(accommodations.getAccommodationDescription() != null){
            this.accommodationDescription = accommodations.getAccommodationDescription();
        }
        if(accommodations.getStudentAccommodationList() != null){
            this.studentAccommodationList = accommodations.getStudentAccommodationList();
        }
    }
}
