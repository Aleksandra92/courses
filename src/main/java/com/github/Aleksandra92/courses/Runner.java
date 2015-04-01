package com.github.Aleksandra92.courses;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.ApiFactory;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class Runner {
    public static void main(String[] args) {

        System.out.println("Полный список групп");
        System.out.println("*******************");
        List<Group> allGroups = ApiFactory.getGroupApiInstance().getGroups();
        for (Group gi : allGroups) {
            System.out.println(gi);
        }
        System.out.println();

        System.out.println("Полный список студентов");
        System.out.println("***********************");
        List<Student> allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Список студентов по группам");
        System.out.println("***************************");
        List<Group> groups = ApiFactory.getGroupApiInstance().getGroups();
        for (Group gi : groups) {
            System.out.println("---> Группа:" + gi.getGroupName());
            List<Student> students = ApiFactory.getStudentApiInstance().getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                System.out.println(si);
            }
        }
        System.out.println();

        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setMiddleName("Владимирович");
        s.setLastName("Перебежкин");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        System.out.println("Добавление студента:" + s);
        System.out.println("********************");
        ApiFactory.getStudentApiInstance().insertStudent(s);
        System.out.println("--->> Полный список студентов после добавления");
        allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setMiddleName("Владимирович");
        s.setLastName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        System.out.println("Редактирование данных студента:" + s);
        System.out.println("*******************************");
        ApiFactory.getStudentApiInstance().updateStudent(s);
        System.out.println("--->> Полный список студентов после редактирования");
        allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Удаление студента:" + s);
        System.out.println("******************");
        ApiFactory.getStudentApiInstance().deleteStudent(s);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        System.out.println("Перевод студентов из 1-ой во 2-ю группу");
        System.out.println("***************************************");
        ApiFactory.getStudentApiInstance().moveStudentsToGroup(g1, 2006, g2, 2007);
        System.out.println("--->> Полный список студентов после перевода");
        allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        System.out.println("Удаление студентов из группы:" + g2 + " в 2006 году");
        System.out.println("*****************************");
        ApiFactory.getStudentApiInstance().removeStudentsFromGroup(g2, 2006);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ApiFactory.getStudentApiInstance().getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }
        System.out.println();
    }
}
