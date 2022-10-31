package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.repositories.CourseRepository;
import com.HackbrightOptum.capstone.repositories.StudentAccommodationRepository;
import com.HackbrightOptum.capstone.repositories.StudentRepository;
import com.HackbrightOptum.capstone.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private TeacherRepository teacherRepository;

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
    public void deleteStudentById(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        studentOptional.ifPresent(student -> studentRepository.delete(student));
    }
    //attempt at deleting student from a course
    @Override
    @Transactional
    public void deleteStudentFromCourse(Long studentId, Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        courseOptional.ifPresent(student -> courseRepository.delete(student));
    }

    //should update be all fields at once or independently?
    @Override
    @Transactional
    public void updateStudentById(StudentDto studentDto){
        Optional<Student> studentOptional = studentRepository.findById(studentDto.getStudentId());
        studentOptional.ifPresent(student -> {

            studentRepository.saveAndFlush(student);
        });
    }

    @Override
    public Optional<StudentDto> getStudentById(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            return Optional.of(new StudentDto(studentOptional.get()));
        }
        return null;
    }

    @Override
    public List<StudentAccommodation> getStudentAccomsById(Long studentId){
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
        List<StudentAccommodation> studentAccommodationList = studentAccommodationRepository.findStudentAccommodationByStudentStudentId(studentId);
        List accommodationList = new ArrayList<>();
        accommodationList.add(studentAccommodationList);
           return studentAccommodationList;
        }


    @Override
    public void createStudent(StudentDto studentDto){
        Student student = new Student(studentDto);
//        StudentAccommodation studentAccommodation= new StudentAccommodation(studentDto);
//        studentAccommodation.setStudent(studentDto.getStudentId());
        for(StudentAccommodationDto studentAccommodationDto : studentDto.getStudentAccommodationList()){
            StudentAccommodation studentAccommodation= new StudentAccommodation(studentDto);
            studentAccommodationRepository.saveAndFlush(studentAccommodation);
            //create entity for the dto
            //save to database
        }
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
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto, Long studentId){
        Optional<StudentAccommodation> studentAccommodationOptional = studentAccommodationRepository.findById(studentAccommodationDto.getStudentId());

        studentAccommodationOptional.ifPresent(studentAccommodation -> {
            studentAccommodation.setAccommodationReceived(studentAccommodationDto.getAccommodationReceived()+1);
            studentAccommodationRepository.saveAndFlush(studentAccommodation);
        });
    }
}
