package com.HackbrightOptum.capstone.controllers;

import com.HackbrightOptum.capstone.dtos.TeacherDto;
import com.HackbrightOptum.capstone.entities.Teacher;
import com.HackbrightOptum.capstone.services.TeacherService;
import com.HackbrightOptum.capstone.dtos.TeacherDto;
import com.HackbrightOptum.capstone.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")

public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //End point tested and works
    @PostMapping("/register")
    public List<String> addTeacher(@RequestBody TeacherDto teacherDto){
        System.out.println(teacherDto);
        String passHash = passwordEncoder.encode(teacherDto.getTeacherPassword());
        teacherDto.setTeacherPassword(passHash);
        return teacherService.addTeacher(teacherDto);
    }

    //End point tested and works
    @PostMapping("/login")
    public List<String> teacherLogin(@RequestBody TeacherDto teacherDto){
        return teacherService.teacherLogin(teacherDto);
    }
}
