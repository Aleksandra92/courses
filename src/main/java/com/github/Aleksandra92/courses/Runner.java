package com.github.Aleksandra92.courses;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.GroupApi;
import com.github.Aleksandra92.courses.service.StudentApi;

import java.util.Calendar;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class Runner {

    public static void main(String[] args) throws Exception {
        final StudentApi studentApi = StudentApi.Factory.getInstance();
        final GroupApi groupApi = GroupApi.Factory.getInstance();

        System.out.println("Полный список групп");
        System.out.println("*******************");
        List<Group> allGroups = groupApi.getGroups();
        for (Group gi : allGroups) {
            System.out.println(gi);
        }
        System.out.println();

        System.out.println("Полный список студентов");
        System.out.println("***********************");
        List<Student> allStudends = studentApi.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Список студентов по группам");
        System.out.println("***************************");
        List<Group> groups = groupApi.getGroups();
        for (Group gi : groups) {
            System.out.println("---> Группа:" + gi.getGroupName());
            List<Student> students = studentApi.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                System.out.println(si);
            }
        }
        System.out.println();

        Student s = new Student();
        s.setFirstName("Игорь");
        s.setMiddleName("Владимирович");
        s.setLastName("Перебежкин");
        s.setSex("М");
        Calendar c = Calendar.getInstance();
        c.set(1991, Calendar.SEPTEMBER, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1L);
        s.setEducationYear(2006);
        System.out.println("Добавление студента:" + s);
        System.out.println("********************");
        studentApi.insertStudent(s);
        System.out.println("--->> Полный список студентов после добавления");
        allStudends = studentApi.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        s = studentApi.getStudent(4L);
        s.setFirstName("Игорь");
        s.setMiddleName("Владимирович");
        s.setLastName("Новоперебежкин");
        s.setSex("М");
        c = Calendar.getInstance();
        c.set(1991, Calendar.SEPTEMBER, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1L);
        s.setEducationYear(2006);
        System.out.println("Редактирование данных студента:" + s);
        System.out.println("*******************************");
        studentApi.updateStudent(s);
        System.out.println("--->> Полный список студентов после редактирования");
        allStudends = studentApi.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Удаление студента:" + s);
        System.out.println("******************");
        studentApi.deleteStudent(s);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = studentApi.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        System.out.println("Перевод студентов из 1-ой во 2-ю группу");
        System.out.println("***************************************");
        studentApi.moveStudentsToGroup(g1, 2006, g2, 2007);
        System.out.println("--->> Полный список студентов после перевода");
        allStudends = studentApi.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Удаление студентов из группы:" + g2 + " в 2006 году");
        System.out.println("*****************************");
        studentApi.removeStudentsFromGroup(g2, 2006);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = studentApi.getAllStudents();
        for (Student student : allStudends) {
            System.out.println(student);
        }
        System.out.println();
    }
}
