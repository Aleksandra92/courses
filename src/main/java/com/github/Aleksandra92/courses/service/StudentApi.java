package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.exceptions.StudentException;
import com.github.Aleksandra92.courses.service.impl.jdbc.StudentApiJdbcImpl;

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
    Student getStudent(Long id) throws StudentException;

    /**
     * Получить список студентов
     *
     * @return Список студентов.
     */
    List<Student> getAllStudents() throws StudentException;

    /**
     * Получить список студентов для определенной группы
     *
     * @param group Группа.
     * @param year Год.
     * @return Список студентов определенной группы.
     */
    List<Student> getStudentsFromGroup(Group group, int year) throws StudentException;

    /**
     * Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
     *
     * @param oldGroup Старая групаа.
     * @param oldYear Старый год.
     * @param newGroup Новая группа.
     * @param newYear Новый обучающий год.
     */
    void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException;

    /**
     * Удалить всех студентов из определенной группы
     *
     * @param group Группа.
     * @param year Год.
     */
    void removeStudentsFromGroup(Group group, int year) throws StudentException;

    /**
     * Обновить данные о студенте
     *
     * @param student Студент.
     */
    void updateStudent(Student student) throws StudentException;

    /**
     * Удалить студенте
     *
     * @param student Студент.
     */
    void deleteStudent(Student student) throws StudentException;

    /**
     * Добавить студента
     *
     * @param student Студент.
     */
    void insertStudent(Student student) throws StudentException;

    void deleteAll() throws StudentException;

    void addAll(List<Student> student) throws StudentException;

    class Factory {
        public static synchronized StudentApi getInstance() throws Exception {
            return StudentApiJdbcImpl.getInstance();
        }
    }
}
