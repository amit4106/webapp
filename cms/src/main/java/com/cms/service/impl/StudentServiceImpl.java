package com.cms.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Student;
import com.cms.exception.StudentNotFoundException;
import com.cms.repository.StudentRepository;
import com.cms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(@Valid Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long studentId) throws StudentNotFoundException {
		Student response = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found for this id : " + studentId));
		return response;
	}

	@Override
	public Student updateStudentById(Long studentId, @Valid Student student) throws StudentNotFoundException {
		Student response = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found for this id : " + studentId));
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setMobile(student.getMobile());
		response.setEmail(student.getEmail());
		final Student saveStudent = studentRepository.save(response);
		return saveStudent;
	}

	@Override
	public String deleteStudentById(Long studentId) {
		Student response = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found for this id : " + studentId));
		studentRepository.delete(response);
		return ("Student has been deleted successfully for the id :"+studentId);
	}
}
