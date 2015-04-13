package com.github.Aleksandra92.courses.service.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.StudentApi;
import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



/**
 * Author: Aleksandra Perova. Created on 09.04.2015.
 */
public class StudentApiJdbcImplTest {
    private Long studentId;

    @Before
    public void setUp() throws Exception {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        Calendar c = Calendar.getInstance();
        student.setFirstName("Иван");
        student.setMiddleName("Сергеевич");
        student.setLastName("Степанов");
        student.setSex("М");
        c.set(1990, Calendar.MARCH, 20);
        student.setDateOfBirth(c.getTime());
        student.setGroupId(1L);
        student.setEducationYear(2006);
        students.add(student);
        StudentApi.Factory.getInstance().addAll(students);
        this.studentId = student.getId();
    }

    @After
    public void tearDown() throws Exception {
        StudentApi.Factory.getInstance().delete(this.studentId);
    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(studentId);
        Assert.assertNotNull(student);
        Assert.assertEquals("Иван", student.getFirstName());
    }

    @Ignore
    public void testGetAllStudents() throws Exception {
        StudentApi.Factory.getInstance().getAllStudents();
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getAllStudents().size());
    }

    @Ignore
    public void testGetStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        int year = 2006;
        StudentApi.Factory.getInstance().getStudentsFromGroup(group, year);
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Ignore
    public void testMoveStudentsToGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        Group group1 = new Group();
        group1.setId(2L);
        int year = 2006;
        int year1=2007;
        StudentApi.Factory.getInstance().moveStudentsToGroup(group1, year1, group, year);
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Ignore
    public void testRemoveStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(2L);
        int year = 2007;
        StudentApi.Factory.getInstance().removeStudentsFromGroup(group, year);
        Assert.assertEquals(0, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(studentId);
        String lastName = "Новое имя";
        student.setLastName(lastName);
        StudentApi.Factory.getInstance().updateStudent(student);
        Assert.assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(studentId);
        StudentApi.Factory.getInstance().deleteStudent(student);
        Assert.assertNull(StudentApi.Factory.getInstance().getStudent(studentId));
    }

    @Test
    public void testDeleteById() throws Exception {
        StudentApi.Factory.getInstance().delete(studentId);
        Assert.assertNull(StudentApi.Factory.getInstance().getStudent(studentId));
    }

    @Test
    public void testInsertStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(studentId);
        StudentApi.Factory.getInstance().insertStudent(student);
        Assert.assertNotNull(student);
    }

    @Ignore
    public void testDeleteAll() throws Exception {
        StudentApi.Factory.getInstance().deleteAll();
        Assert.assertEquals(0, StudentApi.Factory.getInstance().getAllStudents().size());

    }

    @Test
    public void testAddAll() throws Exception {
        List<Student> students = new ArrayList<>();
        Student s = new Student();
        Calendar c = Calendar.getInstance();
        s.setFirstName("Иван");
        s.setMiddleName("Сергеевич");
        s.setLastName("Степанов");
        s.setSex("М");
        c.set(1990, Calendar.MARCH, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2L);
        s.setEducationYear(2007);
        students.add(s);

        s = new Student();
        s.setFirstName("Наталья");
        s.setMiddleName("Андреевна");
        s.setLastName("Чичикова");
        s.setSex("Ж");
        c.set(1990, Calendar.JUNE, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2L);
        s.setEducationYear(2007);
        students.add(s);

        int currentSize = StudentApi.Factory.getInstance().getAllStudents().size();
        StudentApi.Factory.getInstance().addAll(students);
        Assert.assertEquals(currentSize + 2, StudentApi.Factory.getInstance().getAllStudents().size());
    }
}