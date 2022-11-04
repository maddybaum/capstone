package com.HackbrightOptum.capstone.controllers;

import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")

public class CourseController {
    @Autowired
    private CourseService courseService;


    //This is leading to an infinite loop

    @GetMapping("teacher/{teacherId}")
    public List<CourseDto> getCoursesByTeacher(@PathVariable Long teacherId){
        return courseService.getAllCoursesByTeacherId(teacherId);
    }

    //This end point works
    @PostMapping("/{teacherId}")
    public void addCourse(@RequestBody CourseDto courseDto, @PathVariable Long teacherId){
        courseService.addCourse(courseDto, teacherId);
    }
    //works
    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable Long courseId){
        courseService.deleteCourseById(courseId);
    }

    //works but teacher is left null
    @GetMapping("/{courseId}")
    public List<StudentDto> getCourseStudentsByID(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
    }
    //Works
    @PutMapping("/{courseId}")
    public void increaseCourseElapsed(@PathVariable Long courseId){
        courseService.increaseCourseElapsed(courseId);
    }
    @GetMapping
    public List<CourseDto> getAllCourses(){
        return courseService.getAllCourses();
    }

}
