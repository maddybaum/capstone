package com.HackbrightOptum.capstone.repositories;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    public Optional<Student> findStudentById

//    Student findByStudentId(Long studentId);
//
//    List<StudentDto> findByCourseId(Long courseId);

//    List<StudentAccommodationDto> findByAccommodationId (Long accommodationId);
}
