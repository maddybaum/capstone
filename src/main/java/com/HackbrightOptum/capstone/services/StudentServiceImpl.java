package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.CourseDto;
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
import java.util.stream.Collectors;

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
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        studentOptional.ifPresent(student -> studentRepository.delete(student));
    }

    //attempt at deleting student from a course
    @Override
    @Transactional
    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        courseOptional.ifPresent(student -> courseRepository.delete(student));
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
            for(StudentAccommodation studentAccommodation : studentAccommodationList){
                StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto();
                studentAccommodationDto.setAccommodationId(studentAccommodation.getStudentAccommodationId());
                studentAccommodationDto.setAccommodationReceived(studentAccommodation.getAccommodationReceived());
                studentAccommodationDto.setAccommodationFrequency(studentAccommodation.getAccommodationFrequency());
                studentAccommodationDto.setStudentId(studentId);

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

        studentAccommodationDto.setStudentId(studentDto.getStudentId());
//        student.setStudentId(studentDto.getStudentId());
//        StudentAccommodation studentAccommodation = new StudentAccommodation(student, )
//        Long accommodationId =
//        Accommodations accommodations = new Accommodations(studentDto.getStudentAccommodationList());
//        StudentAccommodation studentAccommodation= new StudentAccommodation(studentDto);
//        studentAccommodation.setStudent(studentDto.getStudentId());
        for (StudentAccommodationDto studentAccommodationDto1 : studentDto.getStudentAccommodationList()) {
//
            Accommodations accommodations = accommodationRepository.findAccommodationsByAccommodationId(studentAccommodationDto1.getAccommodationId());
            StudentAccommodation studentAccommodation = new StudentAccommodation(student, accommodations, studentAccommodationDto);
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
//        studentAccommodationRepository.saveAll(student);
    }


//    public void addStudentToCourse()
//    @Override
//    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto) {
//
//    }


    @Override
    @Transactional
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto) {
        Optional<StudentAccommodation> studentAccommodationOptional = studentAccommodationRepository.findStudentAccommodationByStudentStudentIdAndAccommodationAccommodationId(studentAccommodationDto.getStudentId(), studentAccommodationDto.getAccommodationId());
        if (studentAccommodationOptional.isPresent()) {
            StudentAccommodation studentAccommodation = studentAccommodationOptional.get();
            studentAccommodation.setAccommodationReceived(studentAccommodation.getAccommodationReceived() + 1);
            studentAccommodationRepository.save(studentAccommodation);
            System.out.println("We found it");
        } else {
            System.out.println("The studentAccommodationOptional is failed");
        }

    }

    @Override
    public List<StudentAccommodationDto> getStudentAccommodationsById(Long studentId) {
        return null;
    }

}

