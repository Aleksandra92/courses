package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.exceptions.GroupException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 03.04.2015.
 */
public class GroupDaoJdbcImpl implements GroupDao{

    private static Connection con;
    //List<Group> group;

    private GroupDaoJdbcImpl() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void saveOrUpdate(Group group) throws GroupException {
        String sql = "UPDATE groups SET " +
                "groupName=?, curator=?, speciality=? " +
                "WHERE group_id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, group.getGroupName());
            stmt.setString(2, group.getCurator());
            stmt.setString(3, group.getSpeciality());
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Long id) throws GroupException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM groups WHERE group_id=?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Group group) throws GroupException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM groups WHERE group_id=?");
            stmt.setLong(1, group.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to save or update", e);
        }

    }

    @Override
    public Group get(Long id) throws GroupException {
        return null;
    }

    @Override
    public List<Group> getAll() throws GroupException {
        List<Group> groups = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT group_id, groupName, curator, speciality FROM groups");
            while (rs.next()) {
                Group gr = new Group();
               gr.setId(rs.getLong(1));
                gr.setGroupName(rs.getString(2));
                gr.setCurator(rs.getString(3));
                gr.setSpeciality(rs.getString(4));

                groups.add(gr);
            }
        } catch (SQLException e) {
            throw new GroupException("Unable to save or update", e);
        }
        return groups;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void addAll(List<Group> entity) {

    }
}
