package com.github.Aleksandra92.courses.dao.impl.jdbc.sql;

/**
 * Author: Aleksandra Perova. Created on 23.04.2015.
 */
public class StudentSql {
    public static final String INSERT_SQL = "INSERT INTO students " +
            "(first_name, middle_name, last_name, sex, date_of_birth, group_id, education_year)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE students SET " +
            "first_name=?, middle_name=?, last_name=?, " +
            "sex=?, date_of_birth=?, group_id=?, education_year=? "+
            "WHERE student_id=?";
    public static final String DELETE_SQL = "DELETE FROM students WHERE student_id=?";
    public static final String GET_SQL = "SELECT student_id, first_name, middle_name, last_name, " +
            "sex, date_of_birth, group_id, education_year FROM students " +
            "WHERE student_id=?";
    public static final String GET_ALL_SQL = "SELECT student_id, first_name, middle_name, last_name, " +
            "sex, date_of_birth, group_id, education_year FROM students " +
            "ORDER BY last_name, first_name, middle_name";
    public static final String DELETE_ALL_SQL = "TRUNCATE TABLE students";
    public static final String GET_STUDENTS_FROM_GROUP_SQL = "SELECT student_id, first_name, middle_name, last_name, " +
            "sex, date_of_birth, group_id, education_year FROM students " +
            "WHERE group_id=? AND education_year=? " +
            "ORDER BY last_name, first_name, middle_name";
    public static final String MOVE_STUDENTS_TO_GROUP_SQL = "UPDATE students SET group_id=?, education_year=? " +
            "WHERE group_id=? AND education_year=?";
    public static final String REMOVE_STUDENTS_FROM_GROUP_SQL = "DELETE FROM students WHERE group_id=? AND education_year=?";
}
