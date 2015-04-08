package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.StudentApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class StudentApiInMemoryImplTest {

    @Before
    public void setUp() throws Exception {
        StudentApi.Factory.getInstance().deleteAll();
        StudentApi.Factory.getInstance().addAll(loadStudents());
    }

    @Test
    public void testGetStudent() throws Exception {
        Assert.assertNull(StudentApi.Factory.getInstance().getStudent(6L));
        Student student = StudentApi.Factory.getInstance().getStudent(1L);
        Assert.assertNotNull(student);
        Assert.assertEquals("Иван", student.getFirstName());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        StudentApi.Factory.getInstance().getAllStudents();
        Assert.assertEquals(4, StudentApi.Factory.getInstance().getAllStudents().size());
    }

    @Test
    public void testGetStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        int year = 2006;
        StudentApi.Factory.getInstance().getStudentsFromGroup(group, year);
        Assert.assertEquals(2, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
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
        Assert.assertEquals(2, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
        StudentApi.Factory.getInstance().removeStudentsFromGroup(group, year);
        Assert.assertEquals(0, StudentApi.Factory.getInstance().getStudentsFromGroup(group, year).size());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(1L);
        String lastName = "Новое имя";
        student.setLastName(lastName);
        StudentApi.Factory.getInstance().updateStudent(student);
        student = StudentApi.Factory.getInstance().getStudent(1L);
        Assert.assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(1L);
        StudentApi.Factory.getInstance().deleteStudent(student);
        Assert.assertNull(StudentApi.Factory.getInstance().getStudent(1L));
    }

    @Test
    public void testInsertStudent() throws Exception {
        Student student = StudentApi.Factory.getInstance().getStudent(1L);
        StudentApi.Factory.getInstance().insertStudent(student);
        Assert.assertNotNull(student);
    }

    private List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        Student s = new Student();
        Calendar c = Calendar.getInstance();
        s.setId(1L);
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
        s.setId(2L);
        s.setFirstName("Наталья");
        s.setMiddleName("Андреевна");
        s.setLastName("Чичикова");
        s.setSex("Ж");
        c.set(1990, Calendar.JUNE, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2L);
        s.setEducationYear(2007);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setId(3L);
        s.setFirstName("Петр");
        s.setMiddleName("Викторович");
        s.setLastName("Сушкин");
        s.setSex("М");
        c.set(1991, Calendar.MARCH, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1L);
        students.add(s);

        s = new Student();
        s.setId(4L);
        s.setFirstName("Вероника");
        s.setMiddleName("Сергеевна");
        s.setLastName("Ковалева");
        s.setSex("Ж");
        c.set(1991, Calendar.JULY, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1L);
        students.add(s);

        return students;
    }
}