package com.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Course;
import com.cms.exception.CourseNotFoundException;
import com.cms.repository.CourseRepository;
import com.cms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> findAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Course findCourseById(Long courseId) throws CourseNotFoundException {
		Course response = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found for this id : " + courseId));
		return response;
	}

	@Override
	public Course updateCourseById(Long courseId, Course course) throws CourseNotFoundException {
		Course response = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found for this id : " + courseId));
		response.setCourseName(course.getCourseName());
		final Course save = courseRepository.save(response);
		return save;
	}

	@Override
	public String deleteCourseById(Long courseId) throws CourseNotFoundException{
		Course response=courseRepository.findById(courseId)
				.orElseThrow(()-> new CourseNotFoundException("Course not found for this id : "+courseId));
		courseRepository.delete(response);
		return ("Course has been deleted for the id :"+courseId);
	}
}
