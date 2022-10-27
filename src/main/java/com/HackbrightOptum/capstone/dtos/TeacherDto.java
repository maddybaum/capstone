package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Teacher;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TeacherDto implements Serializable {
    private Long teacherId;
    private String teacherName;
    private String teacherPassword;

    ModelMapper modelMapper = new ModelMapper();

    public TeacherDto(Teacher teacher){
        if(teacher.getTeacherId() != null){
            this.teacherId = teacher.getTeacherId();
        }
        if(teacher.getTeacherName() != null){
            this.teacherName = teacher.getTeacherName();
        }
        if(teacher.getTeacherPassword() != null){
            this.teacherPassword = teacher.getTeacherPassword();
        }
    }
}
