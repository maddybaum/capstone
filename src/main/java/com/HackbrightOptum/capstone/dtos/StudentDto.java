package com.HackbrightOptum.capstone.dtos;

import com.HackbrightOptum.capstone.entities.Course;
import com.HackbrightOptum.capstone.entities.Student;
import com.HackbrightOptum.capstone.entities.StudentAccommodation;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto implements Serializable {
    private Long studentId;
    private String studentName;

    public List<Course> getStudentCourse() {
        return studentCourse;
    }

    //    private List<Course> studentCourses = new ArrayList<>();
    private List<Course> studentCourse;

    public void setStudentAccommodationList(List<StudentAccommodationDto> studentAccommodationList) {
        this.studentAccommodationList = studentAccommodationList;
    }

    private List<StudentAccommodationDto> studentAccommodationList = new ArrayList<>();

    public void addAccommodation(StudentAccommodationDto studentAccommodationDto) {
        studentAccommodationList.add(studentAccommodationDto);
    }

    public StudentDto(Student student) {
        if (student.getStudentId() != null) {
            this.studentId = student.getStudentId();
        }
        if (student.getStudentName() != null) {
            this.studentName = student.getStudentName();
        }
        if (student.getStudentAccommodationList() != null) {
            for (StudentAccommodation studentAccommodation : student.getStudentAccommodationList()) {
                StudentAccommodationDto studentAccommodationDto = new StudentAccommodationDto(studentAccommodation);
                this.addAccommodation(studentAccommodationDto);
            }
            if (student.getCourseList() != null) {
                this.studentCourse = student.getCourseList();
            }
//
        }
    }
}

