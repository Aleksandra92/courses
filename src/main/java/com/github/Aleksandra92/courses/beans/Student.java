package com.github.Aleksandra92.courses.beans;

import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: Aleksandra Perova. Created on 30.03.2015.
 */
public class Student extends Entity implements Comparable {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private char sex;
    private Long groupId;
    private int educationYear;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }

    @Override
    public String toString() {

        return "Студент: " + lastName + " " + firstName + " " + middleName + "," +
                DateFormat.getDateInstance(DateFormat.SHORT).format(dateOfBirth)
                + ", Группа ИД=" + getGroupId() + " Год: " + educationYear;
    }

    public int compareTo(Object o) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), o.toString());
    }
}
