package com.cms.apis;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Course;
import com.cms.service.CourseService;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/createCourse")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course){
		return new ResponseEntity<Course>(courseService.createCourse(course), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAllCourse")
	public ResponseEntity<List<Course>> findAllCourse(){
		return new ResponseEntity<List<Course>>(courseService.findAllCourse(), HttpStatus.OK);
	}
	
	@GetMapping("/findCourseById/{id}")
	public ResponseEntity<Course> findCourseById(@PathVariable("id") Long courseId){
		return new ResponseEntity<Course>(courseService.findCourseById(courseId), HttpStatus.OK);
	}
	
	@PutMapping("/updateCourseById/{id}")
	public ResponseEntity<Course> updateCourseById(@PathVariable("id") Long courseId,@Valid @RequestBody Course course){
		return new ResponseEntity<Course>(courseService.updateCourseById(courseId, course), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCourseById/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable("id") Long courseId){
		return new ResponseEntity<String>(courseService.deleteCourseById(courseId), HttpStatus.ACCEPTED);
	}
	
}
