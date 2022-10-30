package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Teacher;
import com.HackbrightOptum.capstone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//Add students to course?
//Add course to teacher?
@Service
public class CourseServiceImpl implements com.HackbrightOptum.capstone.services.CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private StudentAccommodationRepository studentAccommodationRepository;
    @Override
    @Transactional
    public void addCourse(CourseDto courseDto, Long teacherId){
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        Course course = Course.builder()
                .courseName(courseDto.getCourseName())
                .numberOfCoursesElapsed(courseDto.getNumberOfCoursesElapsed())
                .build();

        teacherOptional.ifPresent(course::setTeacher);

//        Student student1 = Student.builder().studentName("Fred").build();
//        Student student2 = Student.builder().studentName("Bob").build();
//        course.addStudent(student1);
//        course.addStudent(student2);
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
    public void updateCourseTeacher(CourseDto courseDto, Long teacherId) {
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            courseOptional.ifPresent(course -> {
                course.setTeacher(teacherOptional.get());
                courseRepository.saveAndFlush(course);

            });
        }
    }

    //Increases courses elapsed by 1 for each passing day
    @Override
    @Transactional
    public void increaseCourseElapsed(CourseDto courseDto){
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
        if(courseOptional.isPresent()){
            courseDto.setNumberOfCoursesElapsed(courseDto.getNumberOfCoursesElapsed() + 1);
        }

    }

    @Override
    public List<CourseDto> getAllCoursesByTeacherId(Long teacherId){
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if(teacherOptional.isPresent()){
            List<Course> courseList = courseRepository.findAllByTeacherEquals(teacherOptional.get());
            return courseList.stream().map(course -> new CourseDto(course)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

//    @Override
//    @Transactional
//    public void updateCourseStudents(CourseDto courseDto){
//        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
//        courseOptional.ifPresent(course -> {
//            course.setCourseStudents(courseDto.getCourseStudentSet());
//            courseRepository.saveAndFlush(course);
//        });
//    }
    @Override
    public Optional<CourseDto> getCourseById(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isPresent()){
            return Optional.of(new CourseDto(courseOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public void increaseDaysElapsed(CourseDto courseDto) {
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getCourseId());
        courseOptional.ifPresent(course -> {
            ;
            courseDto.setNumberOfCoursesElapsed(courseDto.getNumberOfCoursesElapsed() + 1);
            courseRepository.saveAndFlush(course);
        });
    }
    }

