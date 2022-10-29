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
//    public void createStudentAndAccommodation(StudentDto studentDto, AccommodationsDto accommodationsDto, StudentAccommodationDto studentAccommodationDto){
//        Student student = Student.builder().studentName(studentDto.getStudentName()).build();
////        Accommodations accommodations = Accommodations.builder()
////                .accommodationDescription(accommodationsDto.getAccommodationDescription())
////                .accommodationName(accommodationsDto.getAccommodationName())
////                .build();
//        StudentAccommodation studentAccommodation = StudentAccommodation.builder()
//                .accommodationFrequency(studentAccommodationDto.getAccommodationFrequency())
//                .accommodationReceived(studentAccommodationDto.getAccommodationReceived())
//                .build();
//
//        student.addStudentAccommodation(studentAccommodation);
//        studentAccommodation.addAccommodation(accommodations);
//        studentRepository.save(student);
//    }

    //You should include cascade="all" (if using xml) or cascade=CascadeType.ALL (if using annotations) on your collection mapping.
//    public void createStudent(StudentDto studentDto){
//        Student student = Student.builder()
//                .studentName(studentDto.getStudentName())
//                .courseList(studentDto.getStudentCourses())
////                .studentAccommodations(studentDto.getStudentAccommodations())
//                .build();
//
//        studentRepository.save(student);
//    }

    public void createStudent(StudentDto studentDto){
        Student student = new Student(studentDto);
            student.setStudentName(studentDto.getStudentName());

        StudentAccommodation studentAccommodation = new StudentAccommodation();
        studentAccommodation.setAccommodation(studentDto.getStudentAccommodation());
        studentAccommodation.setAccommodationFrequency(studentDto.getAccommodationFrequency());
        studentAccommodation.setAccommodationReceived(studentDto.getAccommodationReceived());

//        Course course = new Course(studentDto.getStudentCourse());
//            course.setCourseName(studentDto.getStudentCourse());
        student.getStudentAccommodationList().add(studentAccommodation);
        studentRepository.save(student);
    }

//    public List<String> createStudent(StudentDto studentDto){
//        List<String> response = new ArrayList<>();
//        Student student = new Student(studentDto);
//        studentRepository.saveAndFlush(student);
//        response.add("New student successfully added");
//        return response;
//    }

    @Override
    @Transactional
    public void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto){
        Optional<Student> studentOptional = studentRepository.findById(studentAccommodationDto.getStudentId());
        if(studentOptional.isPresent()){
            studentAccommodationDto.setAccommodationReceived(studentAccommodationDto.getAccommodationReceived()+1);
        }
    }
}
