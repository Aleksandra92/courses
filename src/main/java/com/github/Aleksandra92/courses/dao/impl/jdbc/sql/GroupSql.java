package com.github.Aleksandra92.courses.dao.impl.jdbc.sql;

/**
 * Author: Aleksandra Perova. Created on 23.04.2015.
 */
public class GroupSql {
    public static final String INSERT_SQL = "INSERT INTO groups " +
            "(group_name, curator, speciality)" +
            "VALUES (?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE groups SET " +
            "group_name=?, curator=?, speciality=?" +
            "WHERE group_id=?";
    public static final String DELETE_SQL = "DELETE FROM groups WHERE group_id=?";
    public static final String GET_SQL = "SELECT group_id, group_name, curator, speciality " +
            " FROM groups " +
            "WHERE group_id=?";
    public static final String GET_ALL_SQL = "SELECT group_id, group_name, curator, speciality FROM groups";
    public static final String DELETE_ALL_SQL = "TRUNCATE TABLE groups";
}
