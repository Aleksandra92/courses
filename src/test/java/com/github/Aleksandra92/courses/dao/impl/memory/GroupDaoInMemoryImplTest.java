package com.github.Aleksandra92.courses.dao.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class GroupDaoInMemoryImplTest {

    private GroupDao groupDao;

    @Before
    public void setUp() {
        this.groupDao = new GroupDaoInMemoryImpl(loadGroups());
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNull(this.groupDao.get(3L));
        Group group = new Group();
        group.setId(3L);
        group.setGroupName("Вторая");
        group.setCurator("Профессор Преображенский");
        group.setSpeciality("Создание человеков из собачек");
        this.groupDao.saveOrUpdate(group);
        Assert.assertNotNull(this.groupDao.get(3L));
    }

    @Test
    public void testUpdate() throws Exception {
        Group group = this.groupDao.get(1L);
        String curator = "Новый куратор";
        group.setCurator(curator);
        this.groupDao.saveOrUpdate(group);
        group = this.groupDao.get(1L);
        Assert.assertEquals(curator, group.getCurator());
    }

    @Test
    public void testDeleteById() throws Exception {
        this.groupDao.delete(1L);
        Assert.assertNull(this.groupDao.get(1L));
    }

    @Test
    public void testDelete() throws Exception {
        Group group = this.groupDao.get(1L);
        this.groupDao.delete(group);
        Assert.assertNull(this.groupDao.get(1L));
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertNull(this.groupDao.get(3L));
        Group group = this.groupDao.get(1L);
        Assert.assertNotNull(group);
        Assert.assertEquals("Первая", group.getGroupName());
    }

    @Test
    public void testGetAll() throws Exception {
        this.groupDao.getAll();
        Assert.assertEquals(2,this.groupDao.getAll().size());
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