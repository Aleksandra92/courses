package com.github.Aleksandra92.courses.dao.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class StudentDaoInMemoryImpl implements StudentDao {

    List<Student> students;

    public StudentDaoInMemoryImpl(List<Student> students) {
        this.students = new ArrayList<>(students.size());
        addAll(students);
    }

    @Override
    public void saveOrUpdate(Student student) {
        Student updStudent = null;
        for (Student si : students) {
            if (si.getId().equals(student.getId())) {
                updStudent = si;
                break;
            }
        }
        if (updStudent == null) {
            student.setId(Counter.getNextId());
            students.add(student);
        } else {
            updStudent.setFirstName(student.getFirstName());
            updStudent.setMiddleName(student.getMiddleName());
            updStudent.setLastName(student.getLastName());
            updStudent.setSex(student.getSex());
            updStudent.setDateOfBirth(student.getDateOfBirth());
            updStudent.setGroupId(student.getGroupId());
            updStudent.setEducationYear(student.getEducationYear());
        }
    }

    @Override
    public void delete(Long id) {
        Student delStudent = null;
        for (Student si : students) {
            if (si.getId().equals(id)) {
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

    @Override
    public void delete(Student student) {
        delete(student.getId());
    }

    @Override
    public Student get(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void deleteAll() {
        students.clear();
    }

    @Override
    public void addAll(List<Student> students) {
        for (Student student : students) {
            saveOrUpdate(student);
        }
    }

    @Override
    public List<Student> getStudentsFromGroup(Group group, int year) {
        List<Student> l = new ArrayList<>();
        for (Student si : students) {
            if (si.getGroupId().equals(group.getId()) && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId().equals(oldGroup.getId()) && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getId());
                si.setEducationYear(newYear);
            }
        }
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) {
        List<Student> tmp = new ArrayList<>();
        for (Student si : students) {
            if (!Objects.equals(si.getGroupId(), group.getId()) || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }
}
