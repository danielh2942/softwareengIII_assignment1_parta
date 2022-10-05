package com.github.danielh2942.softwareeng.collegelib;

import java.util.Map;
import java.util.HashMap;

import java.time.Period;
import java.time.LocalDate;

/**
 * Person is the abstract base class of all Persons within the collegelib.
 *
 * @author Daniel Hannon (19484286)
 * @version 1.0
 *
 */
public abstract class Person implements Comparable<Person> {
	private static long idTotal = 0;
	private static Map<String,Integer> nameMap = new HashMap<String,Integer>();

	private String name;
	private long id;
	private String userName;
	private LocalDate dateOfBirth;
	
	/**
	 * Construct a Person
	 *
	 * @param name {@link String} The Name of the Person
	 */
	public Person(String name, LocalDate DOB) throws FutureDateException {
		// Check if the Date is in the future
		LocalDate now = LocalDate.now();
		if (DOB.isAfter(now)) {
			throw new FutureDateException();
		}
		
		// Handle the ID number
		this.id = idTotal;
		idTotal++;

		// Name, Age, and Username handling
		this.name = name;
		this.dateOfBirth = DOB;
		
		// username handling
		this.userName = this.name.toLowerCase().replaceAll(" ",".");
		
		// Add Age to userName
		Period dur = Period.between(DOB, now);
		this.userName += dur.getYears();
		// Handle the multiple students with the same name and date of birth issue
		if (nameMap.containsKey(name)) {
			// Duplicate names become [first].[m].[last][age].[instance]
			this.userName += "." + nameMap.get(name).toString();
			nameMap.put(name,nameMap.get(name).intValue() + 1);
		} else {
			nameMap.put(name,1);
		}
	}

	/**
	 *	getUserName returns the username of the person
	 *
	 *	@return {@link String} containing the username of the person
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * getId returns the Id of the Person
	 *
	 * @return {@link long} containing the ID of a given person
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * getName returns the name of a given person
	 *
	 * @return {@link String} the name of the person
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the Date of Birth of the student
	 *
	 * @return {@link LocalDate} containing the DOB of the Student
	 */
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	/**
	 * Get a formatted string containing information about a Person 
	 *
	 * @return String containing formatted Data
	 */
	@Override
	public String toString() {
		return String.format("%15s = %s\n%15s = %s\n %15s = %s\n%15s = %s",
				"ID",
				this.id,
				"Name",
				this.name,
				"Username",
				this.userName,
				"Date Of Birth",
				this.dateOfBirth);
	}
	
	/**
	 * Compare two people based on their ID.
	 *
	 * @param person - The person to compare to
	 *
	 * @return 0 - Same Person,
	 *        <0 - person registered before this person
	 *        >0 - person registered after  this person
	 * */
	@Override
	public int compareTo(Person person) {
		return (int)(this.id - person.id);
	}

	/**
	 * HashCode returns the id of the Person as it's already a long
	 * So I don't see why it can't be used :)
	 */
	@Override
	public int hashCode() {
		return (int)this.id;
	}

	public static long getTotalIds() {
		return idTotal;
	}
}
