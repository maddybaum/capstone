package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentAccommodationRepository studentAccommodationRepository;
    @Override
    @Transactional
    public void deleteStudentById(Long studentId) {
        List<StudentAccommodation> studentAccommodationList = studentAccommodationRepository.findStudentAccommodationsByStudentStudentId(studentId);

        for (StudentAccommodation studentAccommodation : studentAccommodationList) {
            studentAccommodationRepository.delete(studentAccommodation);
        }
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        studentOptional.ifPresent(student -> studentRepository.delete(student));

    }

    @Override
    public void deleteStudentFromCourse(Long studentId, Long courseId) {

    }

    @Override
    @Transactional
    public void updateStudentById(StudentDto studentDto) {
        Optional<Student> studentOptional = studentRepository.findById(studentDto.getStudentId());
        studentOptional.ifPresent(student -> {

            studentRepository.saveAndFlush(student);
        });
    }


    @Override
    public List<StudentAccommodationDto> getStudentAccomsById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            List<StudentAccommodation> studentAccommodationList = studentAccommodationRepository.findStudentAccommodationsByStudentStudentId(studentId);
            List<StudentAccommodationDto> studentAccommodationDtoList = new ArrayList<>();
            //use a for loop to iterate through the list
            for (StudentAccommodation studentAccommodation : studentAccommodationList) {
                StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto();
                studentAccommodationDto.setStudentId(studentAccommodation.getStudent().getStudentId());
                studentAccommodationDto.setStudentName(studentAccommodation.getStudent().getStudentName());
                studentAccommodationDto.setAccommodationName(studentAccommodation.getAccommodation().getAccommodationName());
                studentAccommodationDto.setAccommodationDescription(studentAccommodation.getAccommodation().getAccommodationDescription());
                studentAccommodationDto.setAccommodationId(studentAccommodation.getAccommodation().getAccommodationId());
                studentAccommodationDto.setStudentAccommodationId(studentAccommodation.getStudentAccommodationId());
                studentAccommodationDto.setAccommodationReceived(studentAccommodation.getAccommodationReceived());
                studentAccommodationDto.setAccommodationFrequency(studentAccommodation.getAccommodationFrequency());

                studentAccommodationDtoList.add(studentAccommodationDto);
            }
            return studentAccommodationDtoList;
        }

        return Collections.emptyList();
    }


    @Override
    @Transactional
    public void createStudent(StudentDto studentDto) {

        Student student = new Student(studentDto);
        StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto();
        for (StudentAccommodationDto studentAccommodationDto1 : studentDto.getStudentAccommodationList()) {
//
            Accommodations accommodations = accommodationRepository.findAccommodationsByAccommodationId(studentAccommodationDto1.getAccommodationId());
            studentAccommodationDto.setAccommodationName(accommodations.getAccommodationName());
            StudentAccommodation studentAccommodation = new StudentAccommodation(student, accommodations, studentAccommodationDto);
            studentAccommodation.setAccommodation(accommodations);
            studentAccommodation.setAccommodationFrequency(studentAccommodationDto1.getAccommodationFrequency());
            studentAccommodation.setAccommodationReceived(studentAccommodationDto1.getAccommodationReceived());
            studentAccommodation.setStudent(student);
            studentAccommodationRepository.save(studentAccommodation);
            for (Course course1 : studentDto.getStudentCourse()) {
                Course course = courseRepository.findCourseByCourseId(course1.getCourseId());
                course.addStudent(student);

                courseRepository.saveAndFlush(course);
            }
        }
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto) {

    }





    @Override
    public List<StudentAccommodationDto> getStudentAccommodationsById(Long studentId) {
        return null;
    }

    @Override
    @Transactional
    public void increaseStudentAccommodationReceived(Long studentAccommodationId){
        Optional<StudentAccommodation> studentAccommodationOptional = studentAccommodationRepository.findById(studentAccommodationId);

        if(studentAccommodationOptional.isPresent()){
            StudentAccommodation studentAccommodation = studentAccommodationOptional.get();
            studentAccommodation.setAccommodationReceived(studentAccommodation.getAccommodationReceived()+1);
            studentAccommodationRepository.save(studentAccommodation);
        }
    }
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentDto studentDto = new StudentDto(student);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }
}
