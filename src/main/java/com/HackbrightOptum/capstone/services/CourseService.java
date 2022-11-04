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

    @Transactional
    void updateCourseTeacher(CourseDto courseDto, Long teacherId);

    //Increases courses elapsed by 1 for each passing day
    @Transactional
    void increaseCourseElapsed(CourseDto courseDto);

    //Increases courses elapsed by 1 for each passing day
    @Transactional
    void increaseCourseElapsed(Long courseId);

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
    List<StudentDto> getCourseById(Long courseId);

    void increaseDaysElapsed(CourseDto courseDto);

    List<CourseDto> getAllCourses();
}
