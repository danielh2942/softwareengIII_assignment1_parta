package com.github.danielh2942.softwareeng.collegelib;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {
	@Test void testInitModule() {
		assertThrows(NoLecturerException.class, () ->{
			new CollegeModule("Cool Module",null);
		});

		try {
			Lecturer lec = new Lecturer("John Lecturer", LocalDate.of(1970,1,1));
			CollegeModule mod = new CollegeModule("Test Module", lec);
			assertEquals(mod.getLecturer(),lec);
			assertEquals("Test Module",mod.getModuleName());
		} catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test void testSetLecturer() {
		try {
			Lecturer startLecturer = new Lecturer("A Lecturer", LocalDate.of(1970,1,1));
			Lecturer replaceLecturer = new Lecturer("An Other Lecturer", LocalDate.of(1963,11,22));
			CollegeModule mod = new CollegeModule("Cool Module",startLecturer);

			assertFalse(mod.setLecturer(null));
			assertFalse(mod.setLecturer(startLecturer));

			assertTrue(mod.setLecturer(replaceLecturer));

			assertEquals(mod.getLecturer(), replaceLecturer);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test void testStudentEnrolling() {
		try {
			Lecturer lec = new Lecturer("Paul Paulson", LocalDate.of(2008,9,15));

			Student s1 = new Student("Charlie Haughey", LocalDate.of(1992,1,30));

			CollegeModule mod = new CollegeModule("module",lec);

			assertTrue(mod.enrollStudent(s1));
			assertFalse(mod.enrollStudent(s1));
			assertFalse(mod.enrollStudent(null));

			assertTrue(mod.getStudentsEnrolled().contains(s1));

			assertTrue(mod.removeStudent(s1));
			assertFalse(mod.removeStudent(s1));
			assertFalse(mod.removeStudent(null));
		} catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test void testCompareModules() {
		try {
			Lecturer mLec = new Lecturer("Paul",LocalDate.of(2000,1,1));

			CollegeModule mod = new CollegeModule("Test1",mLec);
			CollegeModule mod2 = new CollegeModule("Test2",mLec);

			assertEquals(mod.compareTo(mod2),-1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
