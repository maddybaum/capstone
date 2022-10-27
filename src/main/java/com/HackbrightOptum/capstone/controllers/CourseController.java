package com.HackbrightOptum.capstone.controllers;

import com.HackbrightOptum.capstone.dtos.CourseDto;
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

    @GetMapping("/teacher/{teacherId}")
    public List<CourseDto> getCoursesByTeacher(@PathVariable Long teacherId){
        return courseService.getAllCoursesByTeacherId(teacherId);
    }
    @PostMapping("/course/{teacherId}")
    public void addCourse(@RequestBody CourseDto courseDto, @PathVariable Long teacherId){
        courseService.addCourse(courseDto, teacherId);
    }

    @PutMapping
    public void updateCourse(@RequestBody CourseDto courseDto, @PathVariable Long teacherId){
        courseService.updateCourseTeacher(courseDto, teacherId);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable Long courseId){
        courseService.deleteCourseById(courseId);
    }

    @GetMapping("/{courseId}")
    public Optional<CourseDto> getCoursesById(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
    }
}
