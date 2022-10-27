package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.AccommodationsDto;
import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;

import javax.transaction.Transactional;
import java.util.Optional;

public interface StudentService {
    //Do I need a method to delete a student from a course and then one to delete a student entirely?
    @Transactional
    void deleteStudentById(Long studentId);

    //attempt at deleting student from a course
    @Transactional
    void deleteStudentFromCourse(Long studentId, Long courseId);

    //should update be all fields at once or independently?
    @Transactional
    void updateStudentById(StudentDto studentDto);

    Optional<StudentDto> getStudentById(Long studentId);

    void createStudentAndAccommodation(StudentDto studentDto, AccommodationsDto accommodationsDto, StudentAccommodationDto studentAccommodationDto);

    @Transactional
    void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto);
}
