package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.CourseDto;
import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;
import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import com.HackbrightOptum.capstone.entities.Teacher;
import com.HackbrightOptum.capstone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    @Transactional
    public void addCourse(CourseDto courseDto, Long teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        Course course = Course.builder()
                .courseName(courseDto.getCourseName())
                .numberOfCoursesElapsed(courseDto.getNumberOfCoursesElapsed())
                .build();

        teacherOptional.ifPresent(course::setTeacher);

        courseRepository.saveAndFlush(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        courseOptional.ifPresent(course -> courseRepository.delete(course));
    }

    //Increases courses elapsed by 1 for each passing day
    @Override
    @Transactional
    public void increaseCourseElapsed(Long courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        course.setNumberOfCoursesElapsed(course.getNumberOfCoursesElapsed() + 1);
        courseRepository.save(course);
    }


    @Override
    public List<CourseDto> getAllCoursesByTeacherId(Long teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            List<Course> courseList = courseRepository.findAllByTeacherEquals(teacherOptional.get());
            return courseList.stream().map(course -> new CourseDto(course)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public List<StudentDto> getCourseById(Long courseId) {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            List<StudentDto> studentDtoList= new ArrayList<>();

        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
                for(Student student : course.getStudentList()){
                    StudentDto studentDto = new StudentDto(student);
                    for(StudentAccommodation studentAccommodation : student.getStudentAccommodationList()){
                        StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto(studentAccommodation);
                        studentDto.addAccommodation(studentAccommodationDto);
                    }

                    studentDtoList.add(studentDto);
                }

            }
        return studentDtoList;

    }

    @Override
    public List<CourseDto> getAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courseList){
            CourseDto courseDto = new CourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }
}




