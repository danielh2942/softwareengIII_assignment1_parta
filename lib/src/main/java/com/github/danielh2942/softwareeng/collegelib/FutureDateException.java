package com.github.danielh2942.softwareeng.collegelib;

/**
 * FutureDateException is a special exception used to denote whether
 * or not a date is in the future so that Persons born in the future
 * cannot exist
 *
 * @author Daniel Hannon (19484286)
 * @version 1.0
 */
public class FutureDateException extends Exception {
	private static final long serialVersionUID = 1977465321L;

	/**
	 * Creates a new FutureDateException
	 */
	public FutureDateException() {
		super("Invalid ordering of Dates Passed");
	}
}
