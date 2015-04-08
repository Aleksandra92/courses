package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import com.github.Aleksandra92.courses.service.impl.jdbc.GroupApiJdbcImpl;

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

    void deleteAll() throws GroupException;

    void addAll(List<Group> group) throws GroupException;

    class Factory {
        public static synchronized GroupApi getInstance() throws Exception {
            return GroupApiJdbcImpl.getInstance();
        }
    }
}
