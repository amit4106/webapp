package com.cms.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	@Column(name = "course_name", nullable = false)
	private String courseName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_course", 
	joinColumns = {
			@JoinColumn(name="course_id", referencedColumnName = "courseId")
	},
	inverseJoinColumns = {
			@JoinColumn(name="student_id", referencedColumnName = "studentId")
	}
			)
	private Set<Student> students;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trainer_course",
	joinColumns = {
			@JoinColumn(name="course_id", referencedColumnName = "courseId")
	},
	inverseJoinColumns = {
			@JoinColumn(name="trainer_id", referencedColumnName = "trainerId")
	}
	)
	private Set<Trainer> trainers;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Long courseId, String courseName, Set<Student> students, Set<Trainer> trainers) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.students = students;
		this.trainers = trainers;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(Set<Trainer> trainers) {
		this.trainers = trainers;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", students=" + students + ", trainers="
				+ trainers + "]";
	}
	
	
	
}
