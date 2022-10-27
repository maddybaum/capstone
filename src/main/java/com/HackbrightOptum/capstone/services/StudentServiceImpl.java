package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.AccommodationsDto;
import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Accommodations;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.repositories.CourseRepository;
import com.HackbrightOptum.capstone.repositories.StudentAccommodationRepository;
import com.HackbrightOptum.capstone.repositories.StudentRepository;
import com.HackbrightOptum.capstone.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //How do I match up the student ID and the StudentAccommodation studentID field
//    @Override
//    @Transactional
//    public void updateStudentAccommodationFrequency(StudentAccommodationDto studentAccommodationDto, Long studentId){
//        Optional<Student> accommodationOptional = studentAccommodationRepository.findByStudentId(studentId);
//        if(accommodationOptional.isPresent()){
//            studentAccommodationRepository.
//        }
//    }
    @Override
    public Optional<StudentDto> getStudentById(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            return Optional.of(new StudentDto(studentOptional.get()));
        }
        return null;
    }

    @Override
    public void createStudentAndAccommodation(StudentDto studentDto, AccommodationsDto accommodationsDto, StudentAccommodationDto studentAccommodationDto){
        Student student = Student.builder().studentName(studentDto.getStudentName()).build();
        Accommodations accommodations = Accommodations.builder()
                .accommodationDescription(accommodationsDto.getAccommodationDescription())
                .accommodationName(accommodationsDto.getAccommodationName())
                .build();
        StudentAccommodation studentAccommodation = StudentAccommodation.builder()
                .accommodationFrequency(studentAccommodationDto.getAccommodationFrequency())
                .accommodationReceived(studentAccommodationDto.getAccommodationReceived())
                .build();

        student.addStudentAccommodation(studentAccommodation);
        studentAccommodation.addAccommodation(accommodations);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto){
        Optional<Student> studentOptional = studentRepository.findById(studentAccommodationDto.getStudentId());
        if(studentOptional.isPresent()){
            studentAccommodationDto.setAccommodationReceived(studentAccommodationDto.getAccommodationReceived()+1);
        }
    }
}
