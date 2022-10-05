package com.github.danielh2942.softwareeng.collegelib;

/**
 * NoLecturerException is thrown by CollegeModule
 * if one tries to init without a lecturer or replace a lecturer with null
 *
 * @author Daniel Hannon (19484286)
 * @version 1.0
 */
public class NoLecturerException extends Exception {
	private static final long serialVersionUID = 521328423L;

	/**
	 * Creates a new NoLecturerException
	 */
	public NoLecturerException() {
		super("Modules require lecturers to be initialized");
	}
}
