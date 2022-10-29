package com.HackbrightOptum.capstone.controllers;

import com.HackbrightOptum.capstone.dtos.AccommodationsDto;
import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{studentId}")
    public Optional<StudentDto> getStudentById(@RequestBody StudentDto studentDto, @PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    //This works but it needs a way to add student accomms
    @PostMapping("/newStudent")
    public void createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
    }

//    @PutMapping
//    public void updateStudentById(@RequestBody StudentDto studentDto) {
//        studentService.updateStudentById(studentDto);
//    }

    //Not sure if I wrote endpoint correctly
    @DeleteMapping("course/{courseId}")
    public void deleteStudentFromCourse(@PathVariable CourseDto courseDto, @RequestBody StudentDto studentDto) {
        studentService.deleteStudentFromCourse(courseDto.getCourseId(), studentDto.getStudentId());
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    //This needs to be fixed
    @PutMapping("accomsReceived/{studentId}")
    public void increaseStudentAccommodationReceived(@RequestBody StudentAccommodationDto studentAccommodationDto) {
        studentService.increaseStudentAccommodationReceived(studentAccommodationDto);
    }

}