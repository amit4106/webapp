package com.cms.apis;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Student;
import com.cms.service.StudentService;

import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/createStudent")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		return ResponseEntity.ok(studentService.createStudent(student));
	}

	@GetMapping(value = "/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) {
		return ResponseEntity.ok(studentService.getStudentById(studentId));
	}

	@PutMapping("/updateStudentById/{id}")
	public ResponseEntity<Student> updateStudentById(@PathVariable("id") Long studentId,
			@Valid @RequestBody Student student) {
		return ResponseEntity.ok(studentService.updateStudentById(studentId, student));
	}

	@DeleteMapping("/deleteStudentById/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long studentId) {
		return ResponseEntity.ok(studentService.deleteStudentById(studentId));
	}
}
