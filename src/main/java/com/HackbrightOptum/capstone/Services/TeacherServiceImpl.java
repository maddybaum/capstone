package com.HackbrightOptum.capstone.Services;

import com.HackbrightOptum.capstone.DTOs.TeacherDto;
import com.HackbrightOptum.capstone.Entities.Teacher;
import com.HackbrightOptum.capstone.Repositories.TeacherRepository;
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
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addTeacher(TeacherDto teacherDto){
        List<String> response = new ArrayList<>();
        Teacher teacher = new Teacher(teacherDto);
        teacherRepository.saveAndFlush(teacher);
        response.add("New teacher added successfully");
        return response;
    }
    @Override
    public List<String> teacherLogin(TeacherDto teacherDto){
        List<String> response = new ArrayList<>();
        Optional<Teacher> teacherOptional = teacherRepository.findByUsername(teacherDto.getUsername());
        if(teacherOptional.isPresent()){
            if(passwordEncoder.matches(teacherDto.getPassword(), teacherOptional.get().getPassword())){
                response.add("User login successful");
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
