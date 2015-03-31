package com.github.Aleksandra92.courses;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class Runner {
    public static void main(String[] args) {
//        try {
//            System.setOut(new PrintStream("out.txt"));
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//            return;
//        }

        ManagementSystem ms = ManagementSystem.getInstance();
        System.out.println("Полный список групп");
        System.out.println("*******************");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            System.out.println(gi);
        }
        System.out.println();


        System.out.println("Полный список студентов");
        System.out.println("***********************");
        List<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            //printString(si);
            System.out.println(si);
        }
        //printString();
        System.out.println();

//        printString("Список студентов по группам");
//        printString("***************************");
        System.out.println("Список студентов по группам");
        System.out.println("***************************");
        List<Group> groups = ms.getGroups();
        for (Group gi : groups) {
            //printString("---> Группа:" + gi.getGroupName());
            System.out.println("---> Группа:" + gi.getGroupName());
            List<Student> students = ms.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                //printString(si);
                System.out.println(si);
            }
        }
        //printString();
        System.out.println();

        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setLastName("Перебежкин");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
//        printString("Добавление студента:" + s);
//        printString("********************");
        System.out.println("Добавление студента:" + s);
        System.out.println("********************");
        ms.insertStudent(s);
        //printString("--->> Полный список студентов после добавления");
        System.out.println("--->> Полный список студентов после добавления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            //printString(si);
            System.out.println(si);
        }
        //printString();
        System.out.println();

        s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setLastName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
//        printString("Редактирование данных студента:" + s);
//        printString("*******************************");
        System.out.println("Редактирование данных студента:" + s);
        System.out.println("*******************************");
        ms.updateStudent(s);
        //printString("--->> Полный список студентов после редактирования");
        System.out.println("--->> Полный список студентов после редактирования");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            //printString(si);
            System.out.println(si);
        }
        //printString();
        System.out.println();

//        printString("Удаление студента:" + s);
//        printString("******************");
        System.out.println("Удаление студента:" + s);
        System.out.println("******************");
        ms.deleteStudent(s);
        //printString("--->> Полный список студентов после удаления");
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            //printString(si);
            System.out.println(si);
        }
        //printString();
        System.out.println();

        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
//        printString("Перевод студентов из 1-ой во 2-ю группу");
//        printString("***************************************");
        System.out.println("Перевод студентов из 1-ой во 2-ю группу");
        System.out.println("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        //printString("--->> Полный список студентов после перевода");
        System.out.println("--->> Полный список студентов после перевода");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            //printString(si);
            System.out.println(si);
        }
        //printString();
        System.out.println();

        System.out.println("Удаление студентов из группы:" + g2 + " в 2006 году");
        System.out.println("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }
        System.out.println();
    }
}
