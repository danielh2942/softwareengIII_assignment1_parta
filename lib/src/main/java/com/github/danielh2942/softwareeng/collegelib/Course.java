package com.github.danielh2942.softwareeng.collegelib;

import java.util.Set;
import java.util.HashSet;

import org.joda.time.DateTime;


/**
 * Course contains the courses that one can take in collegelib
 *
 * @author	Daniel Hannon (19484286)
 * @version 1.0
 */
public class Course {
	private DateTime			startDate;
	private DateTime			endDate;
	private String				name;
	private Set<Student>		enrolledStudents;
	private	Set<CollegeModule>	modulesOffered;
	
	/**
	 * Make a Course Object
	 *
	 * @param name      - The name of the course
	 * @param startDate - the Start Date of the Course
	 * @param endDate   - The end date of the course
	 *
	 * @throws FutureDateException - if the startDate is after the endDate
	 */
	public Course(String	name, 
				  DateTime	startDate, 
				  DateTime	endDate) throws FutureDateException {
		this(name,startDate,endDate,new HashSet<CollegeModule>());
	}

	/**
	 * Make a Course Object
	 *
	 * @param name             - The name of the course
	 * @param startDate        - The Start Date of the Course
	 * @param endDate          - The End Date of the Course
	 * @param modulesOffered   - The Modules offered by the Course
	 *
	 * @throws FutureDateException - The Start Date is after the end date
	 */
	public Course(String				name, 
				  DateTime				startDate, 
				  DateTime				endDate, 
				  Set<CollegeModule>	modulesOffered) throws FutureDateException {
		this(name,startDate,endDate,modulesOffered,new HashSet<Student>());
	}

	/**
	 * Make a Course Object
	 *
	 * @param name             - The name of the Course
	 * @param startDate        - The Start Date of the Course
	 * @param endDate          - The End Date of the Course
	 * @param modulesOffered   - The Modules Offered by the Course
	 * @param studentsEnrolled - The students enrolled in the course
	 *
	 * @throws FutureDateException - The start date is after the end date.
	 * */
	public Course(String				name,
				  DateTime				startDate,
				  DateTime				endDate,
				  Set<CollegeModule>	modulesOffered,
				  Set<Student>			enrolledStudents) throws FutureDateException {

		if (startDate.isAfter(endDate)) {
			throw new FutureDateException();
		}

		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.enrolledStudents = enrolledStudents;
		this.modulesOffered = modulesOffered;

		for(Student student: this.enrolledStudents) {
			student.enrollInCourse(this); 
		}
	}

	/**
	 * get the name of the course
	 *
	 * @return String - name of course
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get the start date of the course
	 * 
	 * @return DateTime containing the start date
	 */
	public DateTime getStartDate() {
		return this.startDate;
	}

	/**
	 * get the End Date of the course
	 *
	 * @return DateTime containing the end date
	 */
	public DateTime getEndDate() {
		return this.endDate;
	}

	/**
	 * get a set containing the enrolled students
	 *
	 * @return Set<Student> containing the enrolled students
	 */
	public Set<Student> getEnrolledStudents() {
		return this.enrolledStudents;
	}

	/**
	 * get a set containing the modules offered by the course
	 *
	 * @return Set<CollegeModule> with the modules offered by the course
	 */
	public Set<CollegeModule> getModulesOffered() {
		return this.modulesOffered;
	}

	/**
	 * enroll a student in the course
	 *
	 * @param student - student to enroll
	 * @return boolean corresponding to operation success.
	 */
	public boolean enrollStudentInCourse(Student student) {
		if(student == null || this.enrolledStudents.contains(student)) {
			return false;
		}

		this.enrolledStudents.add(student);
		student.enrollInCourse(this);
		// Enroll the student in a module
		for (CollegeModule module : this.modulesOffered) {
			student.enrollInModule(module);
		}
		return true;
	}

	/**
	 * unenroll a student from the course
	 *
	 * @param student - student to unenroll
	 * @return boolean denoting operation success.
	 */
	public boolean unenrollFromCourse(Student student) {
		if(student == null || !this.enrolledStudents.contains(student)) {
			return false;
		}

		this.enrolledStudents.remove(student);
		student.unenrollFromCourse(this);
		return true;
	}

	/**
	 * Add Module to course
	 *
	 * @param module - module to add
	 * @return boolean - success
	 */
	public boolean addModuleToCourse(CollegeModule module) {
		if(this.modulesOffered.contains(module)) {
			return false;
		}

		this.modulesOffered.add(module);
		return true;
	}

	/**
	 * Print out data for a Course
	 *
	 * @return {@link String} containing information about a course
	 */
	@Override
	public String toString() {
		String outputString = "Course Name: " + this.name;
		outputString += "\nStart Date: " + this.startDate.toString("DD-MM-YY");
		outputString += "\nEnd Date: " + this.endDate.toString("DD-MM-YY");
		outputString += "\nModules Offered:";
		for(CollegeModule m : this.modulesOffered) {
			outputString += "\n\t\t" + m.getModuleName();
		}
		outputString += "\nEnrolled Students:";
		for(Student s : this.enrolledStudents) {
			outputString += "\n\t\t" + s.getName();
		}
		return outputString;
	}
}
