package com.HackbrightOptum.capstone.controllers;

import com.HackbrightOptum.capstone.dtos.AccommodationsDto;
import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.repositories.StudentAccommodationRepository;
import com.HackbrightOptum.capstone.services.CourseService;
import com.HackbrightOptum.capstone.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    //Get student by ID
    //Get student accommodations met
    //Get student accommodations
    //Get student days of school attended

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/accommodations/{studentId}")
    public List<StudentAccommodationDto> getStudentAccomsById(@PathVariable Long studentId){
        return studentService.getStudentAccomsById(studentId);
    }


    @PostMapping("/newStudent")
    public void createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
        System.out.println(studentDto);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    //This needs to be fixed
    @PutMapping("accomsReceived/{studentAccommodationId}")
    public void increaseStudentAccommodationReceived(@PathVariable Long studentAccommodationId) {
        studentService.increaseStudentAccommodationReceived(studentAccommodationId);
        //
    }


    @GetMapping("/allCourses")
    public List<CourseDto> getAllCourses(){
        return courseService.getAllCourses();
    }

@GetMapping
public List<StudentDto> getAllStudents(){
    return studentService.getAllStudents();
}}