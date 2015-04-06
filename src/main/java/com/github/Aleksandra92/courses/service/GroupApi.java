package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.exceptions.GroupException;
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

    void deleteAll();

    void addAll(List<Group> group);

    class Factory {
        public static synchronized GroupApi getInstance() {
            return GroupApiInMemoryImpl.getInstance();
        }
    }
}
