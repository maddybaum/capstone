package com.HackbrightOptum.capstone.repositories;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    List<Course> findAllByTeacherEquals(Teacher teacher);

    Course findCourseByCourseId(Long courseId);



    ;

//    Optional<Course> findCourseBy(Long courseId);

}
