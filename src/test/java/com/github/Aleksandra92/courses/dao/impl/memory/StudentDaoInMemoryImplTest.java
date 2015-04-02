package com.github.Aleksandra92.courses.dao.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class StudentDaoInMemoryImplTest {

    private StudentDao studentDao;

    @Before
    public void setUp() {
        this.studentDao = new StudentDaoInMemoryImpl(loadStudents());
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNull(this.studentDao.get(5L));
        Student student = new Student();
        Calendar c = Calendar.getInstance();
        student.setId(5L);
        student.setFirstName("Иван");
        student.setMiddleName("Сергеевич");
        student.setLastName("Степанов");
        student.setSex('М');
        c.set(1990, Calendar.MARCH, 20);
        student.setDateOfBirth(c.getTime());
        student.setGroupId(2L);
        student.setEducationYear(2007);
        this.studentDao.saveOrUpdate(student);
        Assert.assertNotNull(this.studentDao.get(5L));
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = this.studentDao.get(1L);
        String lastName = "Новое имя";
        student.setLastName(lastName);
        this.studentDao.saveOrUpdate(student);
        student = this.studentDao.get(1L);
        Assert.assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testDeleteById() throws Exception {
        this.studentDao.delete(2L);
        Assert.assertNull(this.studentDao.get(2L));
    }

    @Test
    public void testDelete() throws Exception {
        Student student = this.studentDao.get(1L);
        this.studentDao.delete(student);
        Assert.assertNull(this.studentDao.get(1L));
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertNull(this.studentDao.get(6L));
        Student student = this.studentDao.get(1L);
        Assert.assertNotNull(student);
        Assert.assertEquals("Иван", student.getFirstName());
    }

    @Test
    public void testGetAll() throws Exception {
        this.studentDao.getAll();
        Assert.assertEquals(4,this.studentDao.getAll().size());
    }

    @Test
    public void testGetStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(2L);
        int year = 2007;
        this.studentDao.getStudentsFromGroup(group, year);
        Assert.assertEquals(2, this.studentDao.getStudentsFromGroup(group,year).size());
    }

    @Test
    public void testMoveStudentsToGroup() throws Exception {
        Group group = new Group();
        group.setId(1L);
        Group group1 = new Group();
        group.setId(2L);
        int year1 = 2006;
        int year2=2007;
        this.studentDao.moveStudentsToGroup(group, year1, group1, year2);
        Assert.assertEquals(0, this.studentDao.getStudentsFromGroup(group, year1).size());
    }

    @Test
    public void testRemoveStudentsFromGroup() throws Exception {
        Group group = new Group();
        group.setId(2L);
        int year = 2007;
        Assert.assertEquals(2, this.studentDao.getStudentsFromGroup(group, year).size());
        this.studentDao.removeStudentsFromGroup(group, year);
        Assert.assertEquals(0, this.studentDao.getStudentsFromGroup(group, year).size());

    }

    private List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        Student s = new Student();
        Calendar c = Calendar.getInstance();
        s.setId(1L);
        s.setFirstName("Иван");
        s.setMiddleName("Сергеевич");
        s.setLastName("Степанов");
        s.setSex('М');
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
        s.setSex('Ж');
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
        s.setSex('М');
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
        s.setSex('Ж');
        c.set(1991, Calendar.JULY, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1L);
        students.add(s);

        return students;
    }
}