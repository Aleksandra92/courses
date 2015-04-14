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
public class GroupDaoJdbcImpl implements GroupDao {

    public GroupDaoJdbcImpl() {
    }

    @Override
    public void saveOrUpdate(Group group) throws GroupException {
        String updateSql = "UPDATE groups SET " +
                "group_name=?, curator=?, speciality=?" +
                "WHERE group_id=?";

        String insertSql = "INSERT INTO groups " +
                "(group_name, curator, speciality)" +
                "VALUES (?, ?, ?)";

        String sql;
        if (group.getId() == null) {
            sql = insertSql;
        } else {
            sql = updateSql;
        }
        try (Connection con = new ConnectionManager().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, group.getGroupName());
            stmt.setString(2, group.getCurator());
            stmt.setString(3, group.getSpeciality());
            if (group.getId() != null) {
                stmt.setLong(4, group.getId());
            }
            stmt.execute();
            if (group.getId() == null) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        group.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new GroupException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Long id) throws GroupException {
        String sql = "DELETE FROM groups WHERE group_id=?";
        try (Connection con = new ConnectionManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to delete by id", e);
        }
    }

    @Override
    public void delete(Group group) throws GroupException {
        String sql = "DELETE FROM groups WHERE group_id=?";
        try (Connection con = new ConnectionManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setLong(1, group.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to delete", e);
        }
    }

    @Override
    public Group get(Long id) throws GroupException {
        String sql = "SELECT group_id, group_name, curator, speciality " +
                " FROM groups " +
                "WHERE group_id=?";
        try (Connection con = new ConnectionManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapGroup(rs);
            }

        } catch (SQLException e) {
            throw new GroupException("Unable to get", e);
        }
        return null;
    }

    @Override
    public List<Group> getAll() throws GroupException {
        List<Group> groups = new ArrayList<>();

        String sql = "SELECT group_id, group_name, curator, speciality FROM groups";
        try (Connection con = new ConnectionManager().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                groups.add(mapGroup(rs));
            }
        } catch (SQLException e) {
            throw new GroupException("Unable to getAll", e);
        }
        return groups;
    }

    @Override
    public void deleteAll() throws GroupException {
        String sql = "TRUNCATE TABLE groups";
        try (Connection con = new ConnectionManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
        } catch (SQLException e) {
            throw new GroupException("Unable to delete All", e);
        }
    }

    @Override
    public void addAll(List<Group> groups) {
        for (Group group : groups) {
            try {
                saveOrUpdate(group);
            } catch (GroupException e) {
                e.printStackTrace();
            }
        }
    }

    private Group mapGroup(ResultSet rs) throws SQLException {
        Group gr = new Group();
        gr.setId(rs.getLong(1));
        gr.setGroupName(rs.getString(2));
        gr.setCurator(rs.getString(3));
        gr.setSpeciality(rs.getString(4));
        return gr;
    }
}
