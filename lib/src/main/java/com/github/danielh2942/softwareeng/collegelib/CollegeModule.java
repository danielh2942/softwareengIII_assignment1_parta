package com.github.danielh2942.softwareeng.collegelib;

import java.util.Set;
import java.util.HashSet;

/**
 * Module defines a class that a Student can take
 *
 * @author Daniel Hannon (19484286)
 * @version 1.0
 */
public class CollegeModule implements Comparable<CollegeModule> {

	private String		     name;
	private Lecturer		 lecturer;
	private HashSet<Student> studentsEnrolled;

	/**
	 * Create a Module
	 *
	 * @param name     - The Name of the Module
	 * @param lecturer - The Lecturer that instructs the module
	 */
	public CollegeModule(String name, Lecturer lecturer) throws NoLecturerException {
		if (lecturer == null) {
			throw new NoLecturerException();
		}
		this.name = name;
		this.lecturer = lecturer;
		this.studentsEnrolled = new HashSet<Student>();
		lecturer.replaceLecturerInModule(this);
	}

	/**
	 * Get the Name of the Module
	 * 
	 * @return String containing the name of the Module
	 */
	public String getModuleName() {
		return this.name;
	}

	/**
	 * Get the Lecturer who Runs the Module
	 *
	 * @return Lecturer in charge
	 */
	public Lecturer getLecturer() {
		return this.lecturer;
	}

	/**
	 * Replace the lecturer that runs the module with a new one
	 *
	 * @param lecturer - The new lecturer
	 * @return boolean - Operation success
	 */
	public boolean setLecturer(Lecturer lecturer) {

		if(lecturer == null || this.lecturer.equals(lecturer)) {
			return false;
		}

		Lecturer temp = this.lecturer;
		this.lecturer = lecturer;
		temp.getReplacedInModule(this,lecturer);
		return true;
	}

	/**
	 * Compare Two modules based on their name
	 *
	 * @param mod, Module to compare
	 * @return Zero - if equal, 
	 *           <0 - if mod name is lexiographically before this, 
	 *           0> - if the other way around
	 */
	public int compareTo(CollegeModule mod) {
		return this.name.compareTo(mod.name);
	}

	/**
	 * Add a student to a module
	 *
	 * @param student - The student to remove
	 * 
	 * @return boolean - false if the student is already present
	 *                   true  if the student wasn't previously enrolled
	 */
	public boolean enrollStudent(Student student) {
		if(student == null || this.studentsEnrolled.contains(student)) {
			return false;
		}

		this.studentsEnrolled.add(student);
		student.enrollInModule(this);
		return true;
	}

	/**
	 * remove a Student from a module
	 *
	 * @param student - The student to remove from the module
	 *
	 * @return boolean - True student was removed.
	 *					 False the Student wasn't removed.
	 */
	public boolean removeStudent(Student student) {
		if(student == null || !this.studentsEnrolled.contains(student)) {
			return false;
		}

		this.studentsEnrolled.remove(student);
		student.unenrollInModule(this);
		return true;
	}
	
	/**
	 * get the Set containing the students enrolled
	 *
	 * @return Set<Stduent> containing the students enrolled.
	 */
	public Set<Student> getStudentsEnrolled() {
		return this.studentsEnrolled;
	}

	/**
	 * Format the Module as a String
	 *
	 * @return String of formatted data
	 */
	@Override
	public String toString() {
		String outputString = "Course Name: " + this.name;
		outputString += "\nLecturer: " + this.lecturer.getName();
		outputString += "\nStudents Enrolled:";
		for(Student s : this.studentsEnrolled) {
			outputString += "\n\t\t"+s.getName();
		}
		return outputString;
	}
}
