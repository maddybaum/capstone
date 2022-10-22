package com.HackbrightOptum.capstone.Repositories;

import com.HackbrightOptum.capstone.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
