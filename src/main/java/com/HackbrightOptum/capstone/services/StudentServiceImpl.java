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

//    @Override
//    public void createStudent(StudentDto studentDto){
//        Student student = new Student(studentDto);
//        studentRepository.saveAndFlush(studentDto);
//    }

    //Do I need a method to delete a student from a course and then one to delete a student entirely?
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

    //should update be all fields at once or independently?
    @Override
    @Transactional
    public void updateStudentById(StudentDto studentDto) {
        Optional<Student> studentOptional = studentRepository.findById(studentDto.getStudentId());
        studentOptional.ifPresent(student -> {

            studentRepository.saveAndFlush(student);
        });
    }

//    @Override
//    public Optional<StudentDto> getStudentById(Long studentId){
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        if(studentOptional.isPresent()){
//            return Optional.of(new StudentDto(studentOptional.get()));
//        }
//        return null;
//    }

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
//                studentAccommodationDto.setStudentId(studentId);

                studentAccommodationDtoList.add(studentAccommodationDto);
            }
            return studentAccommodationDtoList;
        }

        return Collections.emptyList();
    }


    @Override
    @Transactional
    public void createStudent(StudentDto studentDto) {
//        Student student = Student.builder()
//                .studentId(studentDto.getStudentId())
//                .build();
        Student student = new Student(studentDto);
        StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto();
//            Accommodations accommodations = accommodationRepository.findAccommodationsByAccommodationId(studentDto.getStudentAccommodationList();
//        for(StudentAccommodation studentAccommodation : student.getStudentAccommodationList()){
//            StudentAccommodationDto studentAccommodationDto1 = new StudentAccommodationDto(studentAccommodation);
//            studentAccommodationDto.setStudentId(studentAccommodation.getStudent().getStudentId());
//            studentAccommodationDto.setAccommodationName(studentAccommodation.getAccommodation().getAccommodationName());
//            studentAccommodationDto.setAccommodationId();
//        }

//        student.setStudentId(studentDto.getStudentId());
//        StudentAccommodation studentAccommodation = new StudentAccommodation(student, )
//        Long accommodationId =
//        Accommodations accommodations = new Accommodations(studentDto.getStudentAccommodationList());
//        StudentAccommodation studentAccommodation= new StudentAccommodation(studentDto);
//        studentAccommodation.setStudent(studentDto.getStudentId());
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

//            course1.setTeacher(course.getTeacher());
//            course1.setCourseId(course.getCourseId());

                courseRepository.saveAndFlush(course);
            }
        }
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto) {

    }


//    public void addStudentToCourse()
//    @Override
//    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto) {
//
//    }




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
