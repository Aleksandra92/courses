package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.dao.impl.memory.GroupDaoInMemoryImpl;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import com.github.Aleksandra92.courses.service.GroupApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class GroupApiInMemoryImpl implements GroupApi {

    private static GroupApiInMemoryImpl instance;
    private GroupDao groupDao;

    private GroupApiInMemoryImpl() {
        this.groupDao = new GroupDaoInMemoryImpl(loadGroups());
    }

    public static synchronized GroupApi getInstance() {
        if (instance == null) {
            instance = new GroupApiInMemoryImpl();
        }
        return instance;
    }

    @Override
    public List<Group> getGroups() throws GroupException {
        return groupDao.getAll();
    }

    @Override
    public void saveOrUpdate(Group group) throws GroupException {
        groupDao.saveOrUpdate(group);
    }

    @Override
    public void delete(Long id) throws GroupException{
        groupDao.delete(id);
    }

    @Override
    public void delete(Group group) throws GroupException {
        groupDao.delete(group);
    }

    @Override
    public Group get(Long id) throws GroupException {
        return groupDao.get(id);
    }

    @Override
    public void deleteAll() throws GroupException {
        groupDao.deleteAll();
    }

    @Override
    public void addAll(List<Group> group) throws GroupException {
        groupDao.addAll(group);
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
