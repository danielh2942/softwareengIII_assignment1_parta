package com.github.danielh2942.softwareeng.collegelib;

import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;

/**
 * Student is the class describing a student in collegelib
 * 
 * @author Daniel Hannon(19484286)
 * @version 1.0
 */
public class Student extends Person {
	
	private Set<CollegeModule> modulesEnrolled;
	private Set<Course>		   coursesEnrolled;

	/**
	 * Constructs a Student Object
	 *
	 * @param name - The full name of the Student.
	 * @param DOB  - The Date of Birth of a Student
	 * @throws FutureDateException - If the Date of Birth is in the future
	 */
	public Student(String name, LocalDate DOB) throws FutureDateException {
		super(name, DOB);
		this.modulesEnrolled = new HashSet<CollegeModule>();
		this.coursesEnrolled = new HashSet<Course>();
	}
	
	/**
	 * Set the list of modules enrolled
	 *
	 * @param modsEnrolled - The full set of modules the student 
	 *        should be enrolled in.
	 */
	public void setModulesEnrolled(HashSet<CollegeModule> modsEnrolled) {
		Set<CollegeModule> tempModules = new HashSet<CollegeModule>(this.modulesEnrolled);
		for (CollegeModule module : tempModules) {
			module.removeStudent(this);
		}
		this.modulesEnrolled = modsEnrolled;
		for (CollegeModule module : this.modulesEnrolled) {
			module.enrollStudent(this);
		}
	}

	/**
	 * Get the list of modules enrolled in
	 *
	 * @return Set containing the modules enrolled in
	 */
	public Set<CollegeModule> getModulesEnrolled() {
		return this.modulesEnrolled;
	}

	/**
	 * Enroll in a given module
	 *
	 * @param module - The module to enroll a student in
	 * 
	 * @return boolean - false if they are already enrolled,
	 *                   true  if they weren't
	 */
	public boolean enrollInModule(CollegeModule module) {
		if (module == null || this.modulesEnrolled.contains(module)) {
			return false;
		}

		this.modulesEnrolled.add(module);
		module.enrollStudent(this);
		return true;
	}

	/**
	 * Make the student leave a given module
	 * 
	 * @param module - The module to be unenrolled from
	 *
	 * @return boolean - true  : Student was in module and is now unenrolled
	 *                   false : Student was not in the module
	 */
	public boolean unenrollInModule(CollegeModule module) {
		if (module == null || !this.modulesEnrolled.contains(module)) {
			return false;
		}

		this.modulesEnrolled.remove(module);
		module.removeStudent(this);
		return true;
	}

	/**
	 * enroll in a given course
	 *
	 * @param course - The course to enroll in
	 *
	 * @return boolean - success of operation
	 */
	public boolean enrollInCourse(Course course) {
		if (course == null || this.coursesEnrolled.contains(course)) {
			return false;
		}

		this.coursesEnrolled.add(course);
		course.enrollStudentInCourse(this);
		return true;
	}

	/**
	 * unenroll from a given course
	 *
	 * @param course - the course to unenroll from
	 *
	 * @return boolean - success of operation
	 */
	public boolean unenrollFromCourse(Course course) {
		if (course == null || !this.coursesEnrolled.contains(course)) {
			return false;
		}

		this.coursesEnrolled.remove(course);
		course.unenrollFromCourse(this);
		return false;
	}

	/**
	 * Get Pretty String for the Student type
	 *
	 * @return String formatted nicely containing all required info
	 *                about a given student.
	 */
	@Override
	public String toString() {
		String output = String.format("%15s = Student\n%s",
							          "Occupation",
							          super.toString());
		
		output += String.format("\n%15s = %s","Courses",this.coursesEnrolled.toString()); 

		boolean firstModule = true;
		for(CollegeModule module : this.modulesEnrolled) {
			if (firstModule) {
				firstModule = false;
				output+= String.format("\n%15s = %s","Enrolled in",module.getModuleName());
			} else {
				output += String.format(",\n%18s","",module.getModuleName());
			}
		}
		return output;
	}
}

