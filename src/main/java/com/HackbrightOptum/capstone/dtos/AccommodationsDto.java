package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccommodationsDto implements Serializable {
    private Long accommodationId;
    private String accommodationName;
    private String accommodationDescription;
    private List<StudentAccommodation> studentAccomList;
    private List<StudentAccommodation> studentAccommodationList;

    //private AccommodationsDto accommodationsDto;

    public AccommodationsDto(Accommodations accommodations){
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
            this.studentAccomList = accommodations.getStudentAccommodationList();
        }
        if(studentAccommodationList != null){
            this.studentAccommodationList = accommodations.getStudentAccommodationList();
        }
    }
}
