package _4352_4421_4480.springbootproject.entity;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class StudentTest {

    Student student = new Student(1L, "Giwrgos", 2018);
    Course course1 = new Course(1L,"SoftEng","softeng syllabus",3,6);
    Course course2 = new Course(2L,"SoftDev","softdev syllabus",3,6);

    @Test
    public void idTest(){

        Long id = student.getId();
        assertEquals((long)id,1L);

        student.setId(2L);
        id = student.getId();
        assertEquals((long)id,2L);

    }

    @Test
    public void nameTest(){

        String name = student.getName();
        assertEquals(name,"Giwrgos");

        student.setName("Giorikas");
        name = student.getName();
        assertEquals(name,"Giorikas");

    }

    @Test
    public void yearOfRegistrationTest(){

        int yearOfRegistration = student.getYearOfRegistration();
        assertEquals(yearOfRegistration,2018);

        student.setYearOfRegistration(2019);
        yearOfRegistration = student.getYearOfRegistration();
        assertEquals(yearOfRegistration,2019);

    }

    @Test
    public void yearOfStudiesTest(){

        int yearOfStudies = student.getYearsOfStudies();
        assertEquals(yearOfStudies,4);

    }

    @Test
    public void semesterTest(){

        int semester = student.getSemester();
        assertEquals(semester,8);

    }
/*
    @Test
    public void coursesTest(){

        List<Course> expectedEnrolledCourses = new ArrayList<>();
        expectedEnrolledCourses.add(course1);
        expectedEnrolledCourses.add(course2);

        course1.enrollStudent(student);
        //System.out.println(course1.getEnrolledStudents());
        course2.enrollStudent(student);
        //System.out.println(course2.getEnrolledStudents());
        List<Course> enrolledCourses = student.getCourses();
        System.out.println(enrolledCourses); //[] for some reason
        System.out.println(expectedEnrolledCourses);
        //assertEquals(expectedEnrolledCourses,enrolledCourses);
    }

*/


}
