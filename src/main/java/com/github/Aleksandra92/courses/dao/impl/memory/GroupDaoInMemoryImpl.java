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
    public void saveOrUpdate(Group group) {
        Group updGroup = null;
        for (Group gr : groups) {
            if (gr.getId().equals(group.getId())) {
                updGroup = gr;
                break;
            }
        }
        if (updGroup == null) {
            groups.add(group);
        } else {
            updGroup.setGroupName(group.getGroupName());
            updGroup.setCurator(group.getCurator());
            updGroup.setSpeciality(group.getSpeciality());
        }
    }

    @Override
    public void delete(Long id) {
        Group delGroup = null;
        for (Group gr : groups) {
            if (gr.getId().equals(id)) {
                delGroup = gr;
                break;
            }
        }
        groups.remove(delGroup);
    }

    @Override
    public void delete(Group group) {
        delete(group.getId());
    }

    @Override
    public Group get(Long id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public List<Group> getAll() {
        return groups;
    }

}
