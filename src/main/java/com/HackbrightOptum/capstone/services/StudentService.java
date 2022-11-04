package com.HackbrightOptum.capstone.services;

import com.HackbrightOptum.capstone.dtos.StudentAccommodationDto;
import com.HackbrightOptum.capstone.dtos.StudentDto;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentService {
    //Do I need a method to delete a student from a course and then one to delete a student entirely?
    @Transactional
    void deleteStudentById(Long studentId);

    //attempt at deleting student from a course
    @Transactional
    void deleteStudentFromCourse(Long studentId, Long courseId);

    //should update be all fields at once or independently?
    @Transactional
    void updateStudentById(StudentDto studentDto);

//    abstract List<StudentDto> getStudentById(Long studentId);

//    void createStudentAndAccommodation(StudentDto studentDto, AccommodationsDto accommodationsDto, StudentAccommodationDto studentAccommodationDto);

    //    public void createStudentAndAccommodation(StudentDto studentDto, AccommodationsDto accommodationsDto, StudentAccommodationDto studentAccommodationDto){
//        Student student = Student.builder().studentName(studentDto.getStudentName()).build();
////        Accommodations accommodations = Accommodations.builder()
////                .accommodationDescription(accommodationsDto.getAccommodationDescription())
////                .accommodationName(accommodationsDto.getAccommodationName())
////                .build();
//        StudentAccommodation studentAccommodation = StudentAccommodation.builder()
//                .accommodationFrequency(studentAccommodationDto.getAccommodationFrequency())
//                .accommodationReceived(studentAccommodationDto.getAccommodationReceived())
//                .build();
//
//        student.addStudentAccommodation(studentAccommodation);
//        studentAccommodation.addAccommodation(accommodations);
//        studentRepository.save(student);
//    }


    List<StudentAccommodationDto> getStudentAccomsById(Long studentId);

    //    public void createStudent(StudentDto studentDto){
//        Student student = Student.builder()
//                .studentName(studentDto.getStudentName())
////                .courseList(studentDto.getStudentCourses())
////                .studentAccommodations(studentDto.getStudentAccommodations())
//                .build();
//
//        studentRepository.save(student);
//    }
void createStudent(StudentDto studentDto);

//    @Transactional
//    void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto, Long studentId);

    @Transactional
    void increaseStudentAccommodationReceived(StudentAccommodationDto studentAccommodationDto);

    List<StudentAccommodationDto> getStudentAccommodationsById(Long studentId);

    @Transactional
    void increaseStudentAccommodationReceived(Long studentAccommodationId);

    List<StudentDto> getAllStudents();


//    @Transactional
//    void createStudent(StudentDto studentDto);
}
