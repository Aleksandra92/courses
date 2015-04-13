package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.exceptions.GroupException;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 08.04.2015.
 */
public class GroupDaoJdbcImplTest {

    public static final String GROUP_NAME = "Первая";

    private GroupDao groupDao;
    private Long groupId;

    @Before
    public void setUp() throws Exception {
        this.groupDao = new GroupDaoJdbcImpl();
        Group group = new Group();
        group.setGroupName(GROUP_NAME);
        group.setCurator("Доктор Борменталь");
        group.setSpeciality("Создание собачек из человеков");
        this.groupDao.saveOrUpdate(group);
        this.groupId = group.getId();
    }

    @After
    public void tearDown() throws GroupException {
        this.groupDao.delete(this.groupId);
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNotNull(this.groupDao.get(this.groupId));
    }

    @Test
    public void testUpdate() throws Exception {
        Group group = this.groupDao.get(groupId);
        String curator = "Новый куратор";
        group.setCurator(curator);
        this.groupDao.saveOrUpdate(group);
        group = this.groupDao.get(groupId);
        Assert.assertEquals(curator, group.getCurator());
    }

    @Test
    public void testDeleteById() throws Exception {
        this.groupDao.delete(groupId);
        Assert.assertNull(this.groupDao.get(groupId));
    }

    @Test
    public void testDelete() throws Exception {
        Group group = this.groupDao.get(groupId);
        this.groupDao.delete(group);
        Assert.assertNull(this.groupDao.get(groupId));
    }

    @Test
    public void testGet() throws Exception {
        Group group = this.groupDao.get(this.groupId);
        Assert.assertNotNull(group);
        Assert.assertEquals(GROUP_NAME, group.getGroupName());
    }

    @Ignore
    public void testGetAll() throws Exception {
        this.groupDao.getAll();
        Assert.assertEquals(1, this.groupDao.getAll().size());
    }

    @Ignore
    public void testDeleteAll() throws Exception {
        this.groupDao.deleteAll();
        Assert.assertEquals(0, this.groupDao.getAll().size());
    }

    @Ignore
    public void testAddAll() throws Exception {
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

        int currentSize = this.groupDao.getAll().size();
        this.groupDao.addAll(groups);
        Assert.assertEquals(currentSize + 2, this.groupDao.getAll().size());
    }

}