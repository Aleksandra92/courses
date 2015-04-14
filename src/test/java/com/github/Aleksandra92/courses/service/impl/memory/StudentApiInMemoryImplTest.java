package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.Type;
import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.StudentApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;


/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class StudentApiInMemoryImplTest {

    private Long studentId;

    @Before
    public void setUp() throws Exception {
        System.setProperty(Type.class.getSimpleName(), Type.IN_MEMORY.toString());
        StudentApi.Factory.getInstance().deleteAll();
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
        StudentApi.Factory.getInstance().insertStudent(student);
        this.studentId = student.getId();
    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(this.studentId);
        Assert.assertNotNull(student);
        Assert.assertEquals("Иван", student.getFirstName());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        StudentApi.Factory.getInstance().getAllStudents();
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getAllStudents().size());
    }

    @Test
    public void testGetStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        int year = 2006;
        StudentApi.Factory.getInstance().getStudentsFromGroup(group, year);
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Test
    public void testMoveStudentsToGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        Group group1 = new Group();
        group.setId(2L);
        int year1 = 2006;
        int year2=2006;
        StudentApi.Factory.getInstance().moveStudentsToGroup(group, year1, group1, year2);
        Assert.assertEquals(0, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year1).size());
    }

    @Test
    public void testRemoveStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        int year = 2006;
        Assert.assertEquals(1, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
        StudentApi.Factory.getInstance().removeStudentsFromGroup(group, year);
        Assert.assertEquals(0, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(this.studentId);
        String lastName = "Новое имя";
        student.setLastName(lastName);
        StudentApi.Factory.getInstance().updateStudent(student);
        student = StudentApi.Factory.getInstance().getStudent(this.studentId);
        Assert.assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(this.studentId);
        StudentApi.Factory.getInstance().deleteStudent(student);
        Assert.assertNull(StudentApi.Factory.getInstance().getStudent(this.studentId));
    }

    @Test
    public void testInsertStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(this.studentId);
        StudentApi.Factory.getInstance().insertStudent(student);
        Assert.assertNotNull(student);
    }
}