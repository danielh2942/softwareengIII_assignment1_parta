package com.github.danielh2942.softwareeng.collegelib;

import java.util.HashSet;

import java.time.LocalDate;

import org.joda.time.DateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCourse {
	@Test void testCreateCourse() {
		assertThrows(FutureDateException.class, () -> {
		new Course("Foo", new DateTime(2222,1,1,0,0), new DateTime(1970,1,1,0,0));
		});

		try {
			HashSet<Student> hs = new HashSet<>();
			hs.add(new Student("John Otto",LocalDate.of(1990,1,1)));
			hs.add(new Student("Fred Durst", LocalDate.of(1970,1,1)));

			Course course = new Course("Cool", new DateTime(1970,1,1,0,0), new DateTime(1971,1,1,0,0),new HashSet<CollegeModule>(),hs);

			assertTrue(course.getEnrolledStudents().equals(hs));
		} catch(Exception e) {
			assertTrue(false);
		}
	}
}
