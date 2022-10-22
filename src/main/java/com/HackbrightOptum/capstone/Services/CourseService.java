package com.HackbrightOptum.capstone.Services;

import com.HackbrightOptum.capstone.DTOs.CourseDto;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CourseService {
    @Transactional
    void addCourse(CourseDto courseDto, Long teacherId);

    @Transactional
    void deleteCourseById(Long courseId);

    @Transactional
    void updateCourseTeacher(CourseDto courseDto);

    @Transactional
    void updateCourseStudents(CourseDto courseDto);

    Optional<CourseDto> getCourseById(Long courseId);
}
