package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.TeacherDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherService {
    @Transactional
    List<String> addTeacher(TeacherDto teacherDto);

    List<String> teacherLogin(TeacherDto teacherDto);
}
