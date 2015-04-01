package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public interface StudentApi {

    /**
     *Получить список студентов
     * @return
     */
    List<Student> getAllStudents();

    /**
     *Получить список студентов для определенной группы
     * @param group
     * @param year
     * @return
     */
    List<Student> getStudentsFromGroup(Group group, int year);

    /**
     *Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
     * @param oldGroup
     * @param oldYear
     * @param newGroup
     * @param newYear
     */
    void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear);

    /**
     *Удалить всех студентов из определенной группы
     * @param group
     * @param year
     */
    void removeStudentsFromGroup(Group group, int year);

    /**
     *Обновить данные о студенте
     * @param student
     */
    void updateStudent(Student student);

    /**
     *Удалить студенте
     * @param student
     */
    void deleteStudent(Student student);

    /**
     *Добавить студента
     * @param student
     */
    void insertStudent(Student student);
}
