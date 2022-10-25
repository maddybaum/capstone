package com.HackbrightOptum.capstone.Services;

import com.HackbrightOptum.capstone.DTOs.StudentDto;
import com.HackbrightOptum.capstone.Entities.Course;
import com.HackbrightOptum.capstone.Entities.Student;
import com.HackbrightOptum.capstone.Repositories.CourseRepository;
import com.HackbrightOptum.capstone.Repositories.StudentRepository;
import com.HackbrightOptum.capstone.Repositories.TeacherRepository;
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

    @Override
    @Transactional
    public void addStudents(StudentDto studentDto, Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Student student = new Student(studentDto);
        courseOptional.ifPresent(student::setStudentCourses);
        studentRepository.saveAndFlush(student);
    }

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
            student.setDaysAtSchool(studentDto.getDaysAtSchool());
            student.setStudentCourses(studentDto.getNewCourse());
            student.setHas15MinBreak(studentDto.getHas15MinBreak());
            student.setHasCheckIn(studentDto.getHasCheckIn());
            student.setHasFidgetToy(studentDto.getHasFidgetToy());
            student.setHasProximityCheck(studentDto.getHasProximityCheck());
            student.setNumOfAccommodationsProvided(studentDto.getNumOfAccommodationsProvided());
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

}
