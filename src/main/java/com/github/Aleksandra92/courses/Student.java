package com.github.Aleksandra92.courses;

import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: Aleksandra Perova. Created on 30.03.2015.
 */
public class Student implements Comparable {
    private int studentId;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date dateOfBirth;
    private char sex;
    private int groupId;
    private int educationYear;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
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
        String COMMA_SEPARATOR = " ";
        return "Студент: " + lastName + COMMA_SEPARATOR + firstName + COMMA_SEPARATOR + patronymic + "," +
                DateFormat.getDateInstance(DateFormat.SHORT).format(dateOfBirth)
                + ", Группа ИД=" + studentId + " Год: " + educationYear;
    }

    public int compareTo(Object o) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), o.toString());
    }
}
