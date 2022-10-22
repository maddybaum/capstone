package com.HackbrightOptum.capstone.DTOs;

import com.HackbrightOptum.capstone.Entities.Course;
import com.HackbrightOptum.capstone.Entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {
    private Long studentId;
    private String studentName;
    private String teacher;
    private Boolean has15MinBreak;
    private Boolean hasPeerBuddy;
    private Boolean hasCheckIn;
    private Boolean hasFidgetToy;
    private Boolean hasProximityCheck;
    private int daysAtSchool;
    private int numOfAccommodationsProvided;
    private Course newCourse;

    public StudentDto(Student student){
        if (student.getStudentId() != null){
            this.studentId = student.getStudentId();
        }
        if(student.getStudentName() != null){
            this.studentName = student.getStudentName();
        }
        if(student.getHas15MinBreak() != null){
            this.has15MinBreak = student.getHas15MinBreak();
        }
        if(student.getHasPeerBuddy() != null){
            this.hasPeerBuddy = student.getHasPeerBuddy();
        }
        if(student.getHasCheckIn() != null){
            this.hasCheckIn = student.getHasCheckIn();
        }
        if(student.getHasFidgetToy() != null){
            this.hasFidgetToy = student.getHasFidgetToy();
        }
        if(student.getHasProximityCheck() != null){
            this.hasProximityCheck = student.getHasProximityCheck();
        }
        //Should these be 0?
        if(student.getDaysAtSchool() != 0){
            this.daysAtSchool = student.getDaysAtSchool();
        }
        if(student.getNumOfAccommodationsProvided() != 0){
            this.numOfAccommodationsProvided = student.getNumOfAccommodationsProvided();
        }

    }

}
