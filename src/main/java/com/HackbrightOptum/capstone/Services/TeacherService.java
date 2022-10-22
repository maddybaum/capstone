package com.HackbrightOptum.capstone.Services;

import com.HackbrightOptum.capstone.DTOs.TeacherDto;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherService {
    @Transactional
    List<String> addTeacher(TeacherDto teacherDto);

    List<String> teacherLogin(TeacherDto teacherDto);
}
