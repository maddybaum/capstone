package com.HackbrightOptum.capstone.dtos;

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
    private List<Student> studentAccomList;
    private List<StudentAccommodation> studentAccommodationList;

    //private AccommodationsDto accommodationsDto;

}
