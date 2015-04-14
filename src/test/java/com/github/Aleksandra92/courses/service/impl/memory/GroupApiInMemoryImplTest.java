package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.Type;
import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.service.GroupApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class GroupApiInMemoryImplTest {

    public static final String GROUP_NAME = "Первая";
    private Long groupId;

    @Before
    public void setUp() throws Exception {
        System.setProperty(Type.class.getSimpleName(), Type.IN_MEMORY.toString());
        GroupApi.Factory.getInstance().deleteAll();
        Group group = new Group();
        group.setGroupName(GROUP_NAME);
        group.setCurator("Доктор Борменталь");
        group.setSpeciality("Создание собачек из человеков");
        GroupApi.Factory.getInstance().insertGroup(group);
        this.groupId = group.getId();
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNotNull(GroupApi.Factory.getInstance().get(this.groupId));
    }

    @Test
    public void testUpdate() throws Exception {
        Group group = GroupApi.Factory.getInstance().get(groupId);
        String curator = "Новый куратор";
        group.setCurator(curator);
        GroupApi.Factory.getInstance().updateGroup(group);
        group = GroupApi.Factory.getInstance().get(groupId);
        Assert.assertEquals(curator, group.getCurator());
    }

    @Test
    public void testDeleteById() throws Exception {
        GroupApi.Factory.getInstance().delete(groupId);
        Assert.assertNull(GroupApi.Factory.getInstance().get(groupId));
    }

    @Test
    public void testDelete() throws Exception {
        Group group = GroupApi.Factory.getInstance().get(groupId);
        GroupApi.Factory.getInstance().delete(group);
        Assert.assertNull(GroupApi.Factory.getInstance().get(groupId));
    }

    @Test
    public void testGet() throws Exception {
        Group group = GroupApi.Factory.getInstance().get(this.groupId);
        Assert.assertNotNull(group);
        Assert.assertEquals(GROUP_NAME, group.getGroupName());
    }

    @Test
    public void testGetGroups() throws Exception {
        GroupApi.Factory.getInstance().getGroups();
        Assert.assertEquals(1, GroupApi.Factory.getInstance().getGroups().size());
    }

    @Test
    public void testDeleteAll() throws Exception {
        GroupApi.Factory.getInstance().deleteAll();
        Assert.assertEquals(0, GroupApi.Factory.getInstance().getGroups().size());
    }

    @Test
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

        GroupApi.Factory.getInstance().addAll(groups);
        Assert.assertEquals(3, GroupApi.Factory.getInstance().getGroups().size());
    }
}