package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import com.github.Aleksandra92.courses.dao.impl.memory.StudentDaoInMemoryImpl;
import com.github.Aleksandra92.courses.service.StudentApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class StudentApiInMemoryImpl implements StudentApi {

    private StudentDao studentDao;

    public StudentApiInMemoryImpl() {
        this.studentDao = new StudentDaoInMemoryImpl(loadStudents());
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.get(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    public List<Student> getStudentsFromGroup(Group group, int year) {
        return studentDao.getStudentsFromGroup(group, year);
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        studentDao.moveStudentsToGroup(oldGroup, oldYear, newGroup, newYear);
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) {
        studentDao.removeStudentsFromGroup(group, year);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.saveOrUpdate(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.get(student.getId());
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
        s.setEducationYear(2006);
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
        s.setEducationYear(2006);
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
