package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.Type;
import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import com.github.Aleksandra92.courses.service.impl.jdbc.GroupApiJdbcImpl;
import com.github.Aleksandra92.courses.service.impl.memory.GroupApiInMemoryImpl;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public interface GroupApi {

    /**
     *Получить список групп
     * @return Список групп.
     */
    List<Group> getGroups() throws GroupException;

    /**
     * Обновить данные о студенте
     *
     * @param group Студент.
     */
    void updateGroup(Group group) throws GroupException;

    /**
     * Добавить студента
     *
     * @param group Студент.
     */
    void insertGroup(Group group) throws GroupException;

    void delete(Long id) throws GroupException;

    void delete(Group group) throws GroupException;

    Group get(Long id) throws GroupException;

    void deleteAll() throws GroupException;

    void addAll(List<Group> group) throws GroupException;

    class Factory {
        public static synchronized GroupApi getInstance() throws Exception {
            if (Type.JDBC.toString().equals(System.getProperty(Type.class.getSimpleName()))) {
                return GroupApiJdbcImpl.getInstance();
            } else {
                return GroupApiInMemoryImpl.getInstance();
            }
        }
    }
}
