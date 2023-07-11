package com.cms.service;

import java.util.List;

import javax.validation.Valid;

import com.cms.entity.Student;

public interface StudentService {

	Student createStudent(@Valid Student student);

	List<Student> getAllStudents();

	Student getStudentById(Long studentId);

	Student updateStudentById(Long studentId, @Valid Student student);

	String deleteStudentById(Long studentId);

}
