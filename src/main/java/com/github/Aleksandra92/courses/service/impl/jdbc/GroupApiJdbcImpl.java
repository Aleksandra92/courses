package com.github.Aleksandra92.courses.service.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.dao.impl.jdbc.GroupDaoJdbcImpl;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import com.github.Aleksandra92.courses.service.GroupApi;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 06.04.2015.
 */
public class GroupApiJdbcImpl implements GroupApi {

    private static GroupApiJdbcImpl instance;
    private GroupDao groupDao;

    private GroupApiJdbcImpl() throws Exception {
        this.groupDao = new GroupDaoJdbcImpl();
    }

    public static synchronized GroupApi getInstance() throws Exception {
        if (instance == null) {
            instance = new GroupApiJdbcImpl();
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
    public void delete(Long id) throws GroupException {
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
}
