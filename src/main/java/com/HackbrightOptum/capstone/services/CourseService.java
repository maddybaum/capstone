package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.CourseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CourseService {
    @Transactional
    void addCourse(CourseDto courseDto, Long teacherId);

    @Transactional
    void deleteCourseById(Long courseId);

    @Transactional
    void updateCourseTeacher(CourseDto courseDto, Long teacherId);

    //Increases courses elapsed by 1 for each passing day
    @Transactional
    void increaseCourseElapsed(CourseDto courseDto);

    List<CourseDto> getAllCoursesByTeacherId(Long teacherId);

    //    @Override
    //    @Transactional
    //    public void updateCourseStudents(CourseDto courseDto){
    //        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
    //        courseOptional.ifPresent(course -> {
    //            course.setCourseStudents(courseDto.getCourseStudentSet());
    //            courseRepository.saveAndFlush(course);
    //        });
    //    }
    Optional<CourseDto> getCourseById(Long courseId);
}
