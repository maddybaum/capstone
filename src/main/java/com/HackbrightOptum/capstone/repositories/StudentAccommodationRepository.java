package com.HackbrightOptum.capstone.repositories;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAccommodationRepository extends JpaRepository<StudentAccommodation, Long> {
    Optional<Student> findByStudentAccommodationId(Long id);
}
