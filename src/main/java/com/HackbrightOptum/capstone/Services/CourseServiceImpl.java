package com.HackbrightOptum.capstone.Services;

import com.HackbrightOptum.capstone.DTOs.CourseDto;
import com.HackbrightOptum.capstone.Entities.Course;
import com.HackbrightOptum.capstone.Entities.Teacher;
import com.HackbrightOptum.capstone.Repositories.CourseRepository;
import com.HackbrightOptum.capstone.Repositories.StudentRepository;
import com.HackbrightOptum.capstone.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


//Add students to course?
//Add course to teacher?
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void addCourse(CourseDto courseDto, Long teacherId){
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        Course course = new Course(courseDto);
        teacherOptional.ifPresent(course::setCourseTeacher);
        courseRepository.saveAndFlush(course);
    }
    @Override
    @Transactional
    public void deleteCourseById(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        courseOptional.ifPresent(course -> courseRepository.delete(course));
    }
    @Override
    @Transactional
    public void updateCourseTeacher(CourseDto courseDto){
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
        courseOptional.ifPresent(course -> {
            course.setCourseTeacher(courseDto.getCourseTeacher());
            courseRepository.saveAndFlush(course);
        });
    }
    @Override
    @Transactional
    public void updateCourseStudents(CourseDto courseDto){
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
        courseOptional.ifPresent(course -> {
            course.setCourseStudents(courseDto.getCourseStudentSet());
            courseRepository.saveAndFlush(course);
        });
    }
    @Override
    public Optional<CourseDto> getCourseById(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isPresent()){
            return Optional.of(new CourseDto(courseOptional.get()));
        }
        return Optional.empty();
    }
}
