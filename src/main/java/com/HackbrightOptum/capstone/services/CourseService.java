package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;

import javax.transaction.Transactional;
import java.util.List;


public interface CourseService {
    @Transactional
    void addCourse(CourseDto courseDto, Long teacherId);

    @Transactional
    void deleteCourseById(Long courseId);



    //Increases courses elapsed by 1 for each passing day
    @Transactional
    void increaseCourseElapsed(Long courseId);

    List<CourseDto> getAllCoursesByTeacherId(Long teacherId);

    List<StudentDto> getCourseById(Long courseId);


    List<CourseDto> getAllCourses();
}
