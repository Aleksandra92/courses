package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import com.github.Aleksandra92.courses.exceptions.StudentException;
import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Author: Aleksandra Perova. Created on 09.04.2015.
 */
public class StudentDaoJdbcImplTest {

    private StudentDao studentDao;
    private Long studentId;

    @Before
    public void setUp() throws Exception {
        this.studentDao = new StudentDaoJdbcImpl();
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
        this.studentDao.saveOrUpdate(student);
        this.studentId = student.getId();
    }

    @After
    public void tearDown() throws StudentException {
        this.studentDao.delete(this.studentId);
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNotNull(this.studentDao.get(this.studentId));
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = this.studentDao.get(studentId);
        String lastName = "Новое";
        student.setLastName(lastName);
        this.studentDao.saveOrUpdate(student);
        Assert.assertEquals(lastName, student.getLastName());

    }

    @Test
    public void testDeleteById() throws Exception {
        this.studentDao.delete(studentId);
        Assert.assertNull(this.studentDao.get(studentId));
    }

    @Test
    public void testDelete() throws Exception {
        Student student = this.studentDao.get(studentId);
        this.studentDao.delete(student);
        Assert.assertNull(this.studentDao.get(studentId));
    }

    @Test
    public void testGet() throws Exception {
        Student student = this.studentDao.get(studentId);
        Assert.assertNotNull(student);
        Assert.assertEquals("Иван", student.getFirstName());
    }

    @Ignore
    public void testGetAll() throws Exception {
        this.studentDao.getAll();
        Assert.assertEquals(1,this.studentDao.getAll().size());
    }

    @Ignore
    public void testDeleteAll() throws Exception {
        this.studentDao.deleteAll();
        Assert.assertEquals(0, this.studentDao.getAll().size());
    }

    @Ignore
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

        int currentSize = this.studentDao.getAll().size();
        this.studentDao.addAll(students);
        Assert.assertEquals(currentSize + 2, this.studentDao.getAll().size());
    }

    @Ignore
    public void testGetStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        int year = 2006;
        this.studentDao.getStudentsFromGroup(group, year);
        Assert.assertEquals(1, this.studentDao.getStudentsFromGroup(group,year).size());
    }

    @Ignore
    public void testMoveStudentsToGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        Group group1 = new Group();
        group1.setId(2L);
        int year = 2006;
        int year1=2007;
        this.studentDao.moveStudentsToGroup(group1, year1, group, year);
        Assert.assertEquals(4, this.studentDao.getStudentsFromGroup(group, year).size());
    }

    @Ignore
    public void testRemoveStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(2L);
        int year = 2007;
        this.studentDao.removeStudentsFromGroup(group, year);
        Assert.assertEquals(0, this.studentDao.getStudentsFromGroup(group, year).size());

    }
}