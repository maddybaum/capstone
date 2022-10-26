//package com.HackbrightOptum.capstone.Controllers;
//
//import com.HackbrightOptum.capstone.DTOs.TeacherDto;
//import com.HackbrightOptum.capstone.Services.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/teachers")
//
//public class TeacherController {
//    @Autowired
//    private TeacherService teacherService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public List<String> addTeacher(@RequestBody TeacherDto teacherDto){
//        String passHash = passwordEncoder.encode(teacherDto.getPassword());
//        teacherDto.setPassword(passHash);
//        return teacherService.addTeacher(teacherDto);
//    }
//    @PostMapping("/login")
//}
