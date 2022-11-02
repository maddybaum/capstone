package com.HackbrightOptum.capstone.repositories;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAccommodationRepository extends JpaRepository<StudentAccommodation, Long> {
    List<StudentAccommodation> findStudentAccommodationsByStudentStudentId(Long studentId);

    Optional<StudentAccommodation> findStudentAccommodationByStudentStudentIdAndAccommodationAccommodationId(Long studentId, Long accommodationId);

}
