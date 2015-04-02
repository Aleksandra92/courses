package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public interface StudentApi {
    /**
     *
     * @param id Id студента.
     * @return Возвращает Id студента.
     */
    Student getStudent(Long id);

    /**
     * Получить список студентов
     *
     * @return Список студентов.
     */
    List<Student> getAllStudents();

    /**
     * Получить список студентов для определенной группы
     *
     * @param group Группа.
     * @param year Год.
     * @return Список студентов определенной группы.
     */
    List<Student> getStudentsFromGroup(Group group, int year);

    /**
     * Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
     *
     * @param oldGroup Старая групаа.
     * @param oldYear Старый год.
     * @param newGroup Новая группа.
     * @param newYear Новый обучающий год.
     */
    void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear);

    /**
     * Удалить всех студентов из определенной группы
     *
     * @param group Группа.
     * @param year Год.
     */
    void removeStudentsFromGroup(Group group, int year);

    /**
     * Обновить данные о студенте
     *
     * @param student Студент.
     */
    void updateStudent(Student student);

    /**
     * Удалить студенте
     *
     * @param student Студент.
     */
    void deleteStudent(Student student);

    /**
     * Добавить студента
     *
     * @param student Студент.
     */
    void insertStudent(Student student);
}
