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
    public List<StudentAccommodation> getStudentAccomsById(Long studentId) {
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
        List<StudentAccommodation> studentAccommodationList = studentAccommodationRepository.findStudentAccommodationByStudentStudentId(studentId);
        List accommodationList = new ArrayList<>();
        accommodationList.add(studentAccommodationList);
        return studentAccommodationList;
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
//            Accommodations accommodations = new Accommodations();
//            accommodations.setAccommodationId(studentAccommodationDto.getAccommodationId());
//            studentAccommodationDto.setAccommodationFrequency(studentDto.getStudentAccommodationList().getAccommodationFrequency());
//            studentAccommodationDto.setAccommodationReceived(studentAccommodationDto);
            Accommodations accommodations = accommodationRepository.findAccommodationsByAccommodationId(studentAccommodationDto1.getAccommodationId());
            StudentAccommodation studentAccommodation = new StudentAccommodation(student, accommodations, studentAccommodationDto);
            studentAccommodation.setAccommodationFrequency(studentAccommodationDto1.getAccommodationFrequency());
            studentAccommodation.setAccommodationReceived(studentAccommodationDto1.getAccommodationReceived());
            studentAccommodation.setStudent(student);
            studentAccommodationRepository.save(studentAccommodation);
        for(CourseDto courseDto : studentDto.getStudentCourse()){
            Course course = courseRepository.findCourseByCourseId(courseDto.getCourseId());
            courseDto.setTeacher(course.getTeacher());
            courseDto.setCourseId(course.getCourseId());

        }
        }
////            StudentAccommodation studentAccommodation = new StudentAccommodation(student, studentDto.getStudentAccommodationList().get;
////          Long accommodationId = accommodationRepository.findAccommodationsByAccommodationId(studentAccommodationDto.getAccommodationId());
//            StudentAccommodation studentAccommodation;
//            studentAccommodation = StudentAccommodation.builder()
//                    .studentAccommodationId(studentAccommodationDto.getStudentId())
//                    .studentAccommodationId(studentAccommodationDto.getAccommodationId())
//                    .accommodationReceived(studentAccommodationDto.getAccommodationReceived())
//                    .accommodationFrequency(studentAccommodationDto.getAccommodationFrequency())
//                    .build();
//            studentAccommodationRepository.saveAndFlush(studentAccommodation);
//        }

//            studentDto.getStudentAccommodationList(accommodationsOptional);
//            Accommodations accommodations = new Accommodations(studentDto.getStudentAccommodationList();
//            Accommodations accom = studentDto.getStudentAccommodationList()
//            StudentAccommodation studentAccommodation= new StudentAccommodation(student, accommodations, studentAccommodationDto);
//                    (student, studentAccommodation.getAccommodation());
//            studentRepository.save(student);
//            studentAccommodationRepository.save(studentAccommodation);
        //create entity for the dto
        //save to database

        //set the student to the already created student
        //get the accommodation frequency from the dto and set it to the studentAccommodation, do same for accom received
        //Use optionals to verify they gave appropriate accom ID
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
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto, Long studentId) {
        Optional<StudentAccommodation> studentAccommodationOptional = studentAccommodationRepository.findById(studentAccommodationDto.getStudentId());

        studentAccommodationOptional.ifPresent(studentAccommodation -> {
            studentAccommodation.setAccommodationReceived(studentAccommodationDto.getAccommodationReceived() + 1);
            studentAccommodationRepository.saveAndFlush(studentAccommodation);
        });
    }}

//    @Override
//    public List<StudentDto> getStudentById(Long studentId) {
//        System.out.println(studentId);
////        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        //find the student in the DB based on the given ID
////        Student student = studentRepository.findByStudentId(studentId);
////        //create a new DTO
//////        Optional<StudentDto> studentDtoOptional = new StudentDto(student);
//////        //Need to check studentAccommodation repository for student ID
////////        studentDto.setStudentAccommodationList(studentAccommodationRepository.findStudentAccommodationByStudentStudentId(studentDto.getStudentId()));
//////        studentDto.getStudentAccommodationList().addAll(studentAccommodationRepository.findAllByStudentIdEquals(studentId));
////        //add accommodations to the studentDto's list
////
////        List<StudentAccommodation> studentAccommodationsList = studentAccommodationRepository.findStudentAccommodationByStudentStudentId(studentId);
////        return studentAccommodationsList.stream().map(studentAccommodation -> new StudentDto(student)).collect(Collectors.toList());
//
//    }


