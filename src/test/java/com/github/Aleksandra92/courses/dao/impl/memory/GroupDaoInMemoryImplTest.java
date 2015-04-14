package com.github.Aleksandra92.courses.dao.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class GroupDaoInMemoryImplTest {

    public static final String GROUP_NAME = "Первая";
    private GroupDao groupDao;
    private Long groupId;

    @Before
    public void setUp() throws GroupException {
        this.groupDao = new GroupDaoInMemoryImpl(loadGroups());
        Group group = new Group();
        group.setGroupName(GROUP_NAME);
        group.setCurator("Доктор Борменталь");
        group.setSpeciality("Создание собачек из человеков");
        this.groupDao.saveOrUpdate(group);
        this.groupId = group.getId();
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNotNull(this.groupDao.get(this.groupId));
    }

    @Test
    public void testUpdate() throws Exception {
        Group group = this.groupDao.get(this.groupId);
        String curator = "Новый куратор";
        group.setCurator(curator);
        this.groupDao.saveOrUpdate(group);
        group = this.groupDao.get(this.groupId);
        Assert.assertEquals(curator, group.getCurator());
    }

    @Test
    public void testDeleteById() throws Exception {
        this.groupDao.delete(this.groupId);
        Assert.assertNull(this.groupDao.get(this.groupId));
    }

    @Test
    public void testDelete() throws Exception {
        Group group = this.groupDao.get(this.groupId);
        this.groupDao.delete(group);
        Assert.assertNull(this.groupDao.get(this.groupId));
    }

    @Test
    public void testGet() throws Exception {
        Group group = this.groupDao.get(this.groupId);
        Assert.assertNotNull(group);
        Assert.assertEquals(GROUP_NAME, group.getGroupName());
    }

    @Test
    public void testGetAll() throws Exception {
        this.groupDao.getAll();
        Assert.assertEquals(3,this.groupDao.getAll().size());
    }

    private List<Group> loadGroups() {
        List<Group> groups = new ArrayList<>();
        Group g = new Group();
        g.setGroupName("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Group();
        g.setGroupName("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);

        return groups;
    }
}