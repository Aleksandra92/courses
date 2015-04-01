package com.github.Aleksandra92.courses.dao.impl.memory;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class GroupDaoInMemoryImpl implements GroupDao {

    List<Group> groups;

    public GroupDaoInMemoryImpl(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public List<Group> getGroups() {
        return groups;
    }

}
