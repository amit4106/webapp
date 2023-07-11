package com.cms.service;

import java.util.List;

import com.cms.entity.Course;

public interface CourseService {

	Course createCourse(Course course);

	List<Course> findAllCourse();

	Course findCourseById(Long courseId);

	Course updateCourseById(Long courseId, Course course);

	String deleteCourseById(Long courseId);

}
