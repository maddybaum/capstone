package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto implements Serializable {
    private Long studentId;
    private String studentName;

    private Set<CourseDto> studentCourses = new HashSet<>();

    //Do I need below?
    //private StudentDto studentDto;
    public StudentDto(Student student) {
        if (student.getStudentId() != null) {
            this.studentId = student.getStudentId();
        }
        if (student.getStudentName() != null) {
            this.studentName = student.getStudentName();
        }

    }
}
