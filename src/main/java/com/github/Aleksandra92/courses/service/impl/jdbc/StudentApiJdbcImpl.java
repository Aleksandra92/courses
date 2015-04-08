package com.github.Aleksandra92.courses.service.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import com.github.Aleksandra92.courses.dao.impl.jdbc.StudentDaoJdbcImpl;
import com.github.Aleksandra92.courses.exceptions.StudentException;
import com.github.Aleksandra92.courses.service.StudentApi;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 06.04.2015.
 */
public class StudentApiJdbcImpl implements StudentApi {

    private static StudentApiJdbcImpl instance;
    private StudentDao studentDao;

    private StudentApiJdbcImpl() throws Exception {
        studentDao = new StudentDaoJdbcImpl();
    }

    public static synchronized StudentApi getInstance() throws Exception {
        if (instance == null) {
            instance = new StudentApiJdbcImpl();
        }
        return instance;
    }

    @Override
    public Student getStudent(Long id) throws StudentException {
        return studentDao.get(id);
    }

    @Override
    public List<Student> getAllStudents() throws StudentException {
        return studentDao.getAll();
    }

    @Override
    public List<Student> getStudentsFromGroup(Group group, int year) throws StudentException {
        return studentDao.getStudentsFromGroup(group, year);
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException {
        studentDao.moveStudentsToGroup(oldGroup, oldYear, newGroup, newYear);
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) throws StudentException {
        studentDao.removeStudentsFromGroup(group, year);
    }

    @Override
    public void updateStudent(Student student) throws StudentException {
        studentDao.saveOrUpdate(student);
    }

    @Override
    public void deleteStudent(Student student) throws StudentException {
        studentDao.delete(student);
    }

    @Override
    public void insertStudent(Student student) throws StudentException {
        studentDao.saveOrUpdate(student);
    }

    @Override
    public void deleteAll() throws StudentException {
        studentDao.deleteAll();
    }

    @Override
    public void addAll(List<Student> student) throws StudentException {
        studentDao.addAll(student);
    }
}
