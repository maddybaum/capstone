package com.HackbrightOptum.capstone.Entities;

import com.HackbrightOptum.capstone.DTOs.StudentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Students")

public class Student {
    @Id
    @GeneratedValue
    private Long studentId;

    @Column
    private String studentName;

    @Column
    private String teacher;

    @Column
    private Boolean has15MinBreak;

    @Column
    private Boolean hasPeerBuddy;

    @Column
    private Boolean hasCheckIn;

    @Column
    private Boolean hasFidgetToy;

    @Column
    private Boolean hasProximityCheck;

    @Column
    private int daysAtSchool;

    @Column
    private int numOfAccommodationsProvided;


    @ManyToOne
    @JsonManagedReference
    //@JoinColumn(name = "courseId", nullable = false)
    private Course newCourse;

    public Student() {
    }

    //Should this be Teacher teacher?
    public Student(Long studentId, String studentName, String teacher, Boolean has15MinBreak, Boolean hasPeerBuddy, Boolean hasCheckIn, Boolean hasFidgetToy, Boolean hasProximityCheck, int daysAtSchool, int numOfAccommodationsProvided, Course newCourse
    ) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.teacher = teacher;
        this.has15MinBreak = has15MinBreak;
        this.hasPeerBuddy = hasPeerBuddy;
        this.hasCheckIn = hasCheckIn;
        this.hasFidgetToy = hasFidgetToy;
        this.hasProximityCheck = hasProximityCheck;
        this.daysAtSchool = daysAtSchool;
        this.numOfAccommodationsProvided = numOfAccommodationsProvided;
        this.newCourse = newCourse;

    }
    public Student(StudentDto studentDto) {
        if (studentDto.getStudentName() != null) {
            this.studentName = studentDto.getStudentName();
        }
        if (studentDto.getHas15MinBreak() != null) {
            this.has15MinBreak = studentDto.getHas15MinBreak();
        }
        if (studentDto.getHasPeerBuddy() != null) {
            this.hasPeerBuddy = studentDto.getHasPeerBuddy();
        }
        if (studentDto.getHasCheckIn() != null) {
            this.hasCheckIn = studentDto.getHasCheckIn();
        }
        if (studentDto.getHasFidgetToy() != null) {
            this.hasFidgetToy = studentDto.getHasFidgetToy();
        }
        if (studentDto.getHasProximityCheck() != null) {
            this.hasProximityCheck = studentDto.getHasProximityCheck();
        }
        //Should these be 0?
        if (studentDto.getDaysAtSchool() != 0) {
            this.daysAtSchool = studentDto.getDaysAtSchool();
        }
        if (studentDto.getNumOfAccommodationsProvided() != 0) {
            this.numOfAccommodationsProvided = studentDto.getNumOfAccommodationsProvided();
        }
    }
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Boolean getHas15MinBreak() {
        return has15MinBreak;
    }

    public void setHas15MinBreak(Boolean has15MinBreak) {
        this.has15MinBreak = has15MinBreak;
    }

    public Boolean getHasPeerBuddy() {
        return hasPeerBuddy;
    }

    public void setHasPeerBuddy(Boolean hasPeerBuddy) {
        this.hasPeerBuddy = hasPeerBuddy;
    }

    public Boolean getHasCheckIn() {
        return hasCheckIn;
    }

    public void setHasCheckIn(Boolean hasCheckIn) {
        this.hasCheckIn = hasCheckIn;
    }

    public Boolean getHasFidgetToy() {
        return hasFidgetToy;
    }

    public void setHasFidgetToy(Boolean hasFidgetToy) {
        this.hasFidgetToy = hasFidgetToy;
    }

    public Boolean getHasProximityCheck() {
        return hasProximityCheck;
    }

    public void setHasProximityCheck(Boolean hasProximityCheck) {
        this.hasProximityCheck = hasProximityCheck;
    }

    public int getDaysAtSchool() {
        return daysAtSchool;
    }

    public void setDaysAtSchool(int daysAtSchool) {
        this.daysAtSchool = daysAtSchool;
    }

    public int getNumOfAccommodationsProvided() {
        return numOfAccommodationsProvided;
    }

    public void setNumOfAccommodationsProvided(int numOfAccommodationsProvided) {
        this.numOfAccommodationsProvided = numOfAccommodationsProvided;
    }

    public Course getStudentCourse() {
        return newCourse;
    }

    public void setStudentCourses(Course studentCourse) {
        this.newCourse = studentCourse;
    }


}
//one to many class to student
//student many to one to class