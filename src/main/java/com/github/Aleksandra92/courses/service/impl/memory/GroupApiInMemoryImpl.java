package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.dao.impl.memory.GroupDaoInMemoryImpl;
import com.github.Aleksandra92.courses.service.GroupApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class GroupApiInMemoryImpl implements GroupApi {

    private GroupDao groupDao;

    public GroupApiInMemoryImpl() {
        this.groupDao = new GroupDaoInMemoryImpl(loadGroups());
    }

    @Override
    public List<Group> getGroups() {
        return groupDao.getAll();
    }

    private List<Group> loadGroups() {
        List<Group> groups = new ArrayList<>();
        Group g = new Group();
        g.setId(1L);
        g.setGroupName("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Group();
        g.setId(2L);
        g.setGroupName("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);

        return groups;
    }
}
