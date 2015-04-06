package com.github.Aleksandra92.courses.service.impl.memory;

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

    private GroupApi groupApi = GroupApi.Factory.getInstance();

    @Before
    public void setUp() {
        this.groupApi.deleteAll();
        this.groupApi.addAll(loadGroups());
    }

    @Test
    public void testGetGroups() throws Exception {
        this.groupApi.getGroups();
        Assert.assertEquals(2, this.groupApi.getGroups().size());
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