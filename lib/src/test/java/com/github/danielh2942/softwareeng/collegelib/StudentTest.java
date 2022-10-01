package com.github.danielh2942.softwareeng.collegelib;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
	@Test void testInvalidDateInConstructor() {
		Exception ex = assertThrows(FutureDateException.class, () ->
				new Student("Foo Bar",LocalDate.of(2222,1,2)));
		assertEquals("Invalid ordering of Dates Passed", ex.getMessage());
	}
	
	@Test void testGetUserName() {
		try {
			Student t = new Student("Robert Paulson", LocalDate.of(2000,1,1));

			assertEquals(t.getUserName(), "robert.paulson22");
		} catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test void duplicateNameHandling() {
		try {
			Student s1 = new Student("John Murphy", LocalDate.of(1973,1,2));
			Student s2 = new Student("John Murphy", LocalDate.of(1973,1,2));

			assertFalse(s1.getUserName().equals(s2.getUserName()), "Usernames should not match");
			assertEquals(s1.getUserName(),"john.murphy49");
			assertEquals(s2.getUserName(),"john.murphy49.1");
		} catch(Exception e) {
			assertEquals(1,2);
		}
	}

	@Test void testModuleHandling() {
		try {
			Lecturer lec = new Lecturer("John Lecturer", LocalDate.of(1970,1,1));
			CollegeModule module = new CollegeModule("Test Module",lec);

			Student s1 = new Student("Foo Barson", LocalDate.of(2000,1,1));

			assertTrue(s1.enrollInModule(module));
			assertFalse(s1.enrollInModule(null), "Should not be able to enroll in a null module");
			assertFalse(s1.enrollInModule(module), "Should not be able to enroll in the same module twice");
			assertTrue(s1.getModulesEnrolled().contains(module));

			assertTrue(s1.unenrollInModule(module), "Should be able to unenroll from module");
			assertFalse(s1.unenrollInModule(module), "Should not be able to unenroll from a module that you're not enrolled in");
			assertFalse(s1.unenrollInModule(null), "Should not be able to unenroll from null module");
		} catch(Exception e) {
			assertEquals(1,2);
		}
	}
}
