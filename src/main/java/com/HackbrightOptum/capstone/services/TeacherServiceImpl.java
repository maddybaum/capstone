package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.TeacherDto;
import com.HackbrightOptum.capstone.entities.Teacher;
import com.HackbrightOptum.capstone.repositories.TeacherRepository;
import com.HackbrightOptum.capstone.dtos.TeacherDto;
import com.HackbrightOptum.capstone.entities.Teacher;
import com.HackbrightOptum.capstone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    StudentAccommodationRepository studentAccommodationRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addTeacher(TeacherDto teacherDto){
        List<String> response = new ArrayList<>();
//        Teacher teacher = new Teacher(teacherDto);
        Teacher teacher = Teacher.builder()
                .teacherName(teacherDto.getTeacherName())
                .teacherPassword(teacherDto.getTeacherPassword())
                .build();

        teacherRepository.saveAndFlush(teacher);
        response.add("http://localhost:8080/login.html");
        return response;
    }
    @Override
    public List<String> teacherLogin(TeacherDto teacherDto){
        List<String> response = new ArrayList<>();
        Optional<Teacher> teacherOptional = teacherRepository.findByTeacherName(teacherDto.getTeacherName());
        if(teacherOptional.isPresent()){
            if(passwordEncoder.matches(teacherDto.getTeacherPassword(), teacherOptional.get().getTeacherPassword())){
                response.add("http://localhost:8080/dashboard.html");
                response.add(String.valueOf(teacherOptional.get().getTeacherId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }


}
