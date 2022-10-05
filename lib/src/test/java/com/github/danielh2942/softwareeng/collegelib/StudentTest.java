package com.github.danielh2942.softwareeng.collegelib;

import java.util.HashSet;

import java.time.LocalDate;

import org.joda.time.DateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
	@Test void testInvalidDateInConstructor() {
		Exception ex = assertThrows(FutureDateException.class, () ->
				new Student("Foo Bar",LocalDate.of(2222,1,2)));
		assertEquals("Invalid ordering of Dates Passed", ex.getMessage());
	}
	
	@Test void testGetUserDetails() {
		try {
			Student t = new Student("Robert Paulson", LocalDate.of(2000,1,1));

			assertEquals(t.getUserName(), "robert.paulson22");

			assertEquals(t.getDateOfBirth(), LocalDate.of(2000,1,1));

			assertEquals(t.getId(),Person.getTotalIds()-1);

			assertEquals(t.getName(), "Robert Paulson");
		} catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test void testStudentComparison() {
		try {
			Student stu = new Student("Stu Studentsson",LocalDate.of(1990,1,1));
			Student stu2 = new Student("Joe Bloggs",LocalDate.of(1997,1,1));

			assertTrue(stu.compareTo(stu2) == -1);
		} catch(FutureDateException e) {
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
		
			s1.setModulesEnrolled(new HashSet<CollegeModule>());

			assertTrue(s1.getModulesEnrolled().isEmpty());

			s1.setModulesEnrolled(new HashSet<CollegeModule>());
		} catch(Exception e) {
			assertEquals(1,2);
		}
	}

	@Test void testCourseHandling() {
		try {
			Student myStudent = new Student("Paul Student", LocalDate.of(1970,1,1));

			Course myCourse = new Course("Swag 101", new DateTime(1970,1,1,0,0), new DateTime(1971,1,1,0,0));
			assertTrue(myStudent.enrollInCourse(myCourse));

			assertTrue(myStudent.getCoursesEnrolled().contains(myCourse), "Should be enrolled in course");
			assertFalse(myStudent.enrollInCourse(myCourse), "Should not be able to enroll in the same course twice");
			assertFalse(myStudent.enrollInCourse(null),"Should not be able to enroll in null course");
			assertTrue(myStudent.unenrollFromCourse(myCourse));

			assertFalse(myStudent.unenrollFromCourse(myCourse));

			assertFalse(myStudent.unenrollFromCourse(null));
		} catch (FutureDateException e) {
			assertTrue(false);
		}
	}


	@Test void testToString() {
		try {
			Student myStudent = new Student("Paul Johnson", LocalDate.of(2000,1,1));

			Lecturer myLec = new Lecturer("John Lecturer", LocalDate.of(1970,1,1));

			CollegeModule mod1 = new CollegeModule("Fake Module",myLec);
			CollegeModule mod2 = new CollegeModule("Fake Module 2", myLec);
			HashSet<CollegeModule> modsEnrolled = new HashSet<CollegeModule>();

			modsEnrolled.add(mod1);
			modsEnrolled.add(mod2);
			myStudent.setModulesEnrolled(modsEnrolled);
			assertNotEquals(myStudent.toString(),"");
		} catch(Exception e) {
			assertEquals(1,2);
		}
	}
}
