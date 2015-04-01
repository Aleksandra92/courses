package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.beans.Group;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public interface GroupApi {

    /**
     *Получить список групп
     * @return
     */
    List<Group> getGroups();

}
