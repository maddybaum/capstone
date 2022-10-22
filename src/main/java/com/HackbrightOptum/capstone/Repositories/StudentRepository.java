package com.HackbrightOptum.capstone.Repositories;

import com.HackbrightOptum.capstone.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
