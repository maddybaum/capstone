package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Student;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAccommodationDto implements Serializable {
    private Long studentAccommodationId;
    private Student student;
    private Accommodations accommodation;
}
