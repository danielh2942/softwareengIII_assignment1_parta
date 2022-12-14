package com.github.danielh2942.softwareeng.collegelib;

import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Lecturer is the class describing lecturers in collegelib
 *
 * @author Daniel Hannon (19484286)
 * @version 1.0
 */
public class Lecturer extends Person {
	
	private Set<CollegeModule> modulesTaught;

	/**
	 * Constructs a Lecturer Object
	 * 
	 * @param name - The Name of the Lecturer
	 * @param DOB  - The Date of Birth of the Lecturer
	 * @throws FutureDateException if the DOB is in the future
	 */
	public Lecturer(String name, LocalDate DOB) throws FutureDateException {
		super(name, DOB);

		this.modulesTaught = new HashSet<CollegeModule>();
	}
	
	/**
	 * Get a Set containing all the modules taught by a lecturer
	 *
	 * @return Set<CollegeModule> containing the modules taught by a lecturer.
	 */
	public Set<CollegeModule> getModulesTaught() {
		return this.modulesTaught;
	}
	
	/**
	 * Take control of a module from a different lecturer
	 *
	 * @param module - The Module to take over
	 *
	 * @return boolean - Operation success.
	 */
	public boolean replaceLecturerInModule(CollegeModule module) {
		if (module == null || this.modulesTaught.contains(module)) {
			return false;
		}
		try {	
			module.setLecturer(this);
		} catch(Exception e) {
			// This can never ever happen so just eat the exception.
		}
		this.modulesTaught.add(module);
		Lecturer temp = module.getLecturer();
		if (temp != null) {
			temp.getReplacedInModule(module,this);
		}
		return true;
	}

	/**
	 * Be replaced by another lecturer in a module
	 *
	 * @param module - the module to be replaced in
	 * @param lecturer - The lecturer you are being replaced by.
	 *
	 * @return boolean - operation success.
	 */
	public boolean getReplacedInModule(CollegeModule module, Lecturer lecturer) {
		if(module == null 
		  || lecturer == null 
		  || lecturer.equals(this) 
		  || !this.modulesTaught.contains(module)) {
			return false;
		}

		this.modulesTaught.remove(module);
		lecturer.replaceLecturerInModule(module);
		return true;
	}

	/**
	 * Get formatted string with information about a lecturer
	 *
	 * @return {@link String} containing information about a lecturer
	 */
	@Override
	public String toString() {
		String outputString = "Lecturer Name: " + this.getName();
		outputString += "\nID: " + this.getId();
		outputString += "\nUsername: " + this.getUserName();
		outputString += "\nDate of Birth: " + this.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE);
		outputString += "\nModules Taught:";
		for (CollegeModule m : this.modulesTaught) {
			outputString += "\n\t\t"+m.getModuleName();
		}
		return outputString;
	}
}
