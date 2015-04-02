package com.github.Aleksandra92.courses.beans;

/**
 * Author: Aleksandra Perova. Created on 30.03.2015.
 */
public class Group extends Entity {
    private String groupName;
    private String curator;
    private String speciality;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return  groupName;
    }
}
