package com.github.Aleksandra92.courses.dao;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.exceptions.StudentException;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public interface StudentDao extends AbstractDao<Student, StudentException> {

    /**
     * Получить список студентов для определенной группы.
     *
     * @param group Определенная группа.
     * @param year Год.
     * @return Выдает список студентов определенной группы.
     */
    List<Student> getStudentsFromGroup(Group group, int year) throws StudentException;

    /**
     * Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения.
     *
     * @param oldGroup Старая группа.
     * @param oldYear Прошлый год.
     * @param newGroup Новая группа.
     * @param newYear Новый год обучения.
     */
    void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException;

    /**
     * Удалить всех студентов из определенной группы.
     *
     * @param group Группа.
     * @param year Год обучения.
     */
    void removeStudentsFromGroup(Group group, int year) throws StudentException;

}