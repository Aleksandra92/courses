package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.dao.GroupDao;
import com.github.Aleksandra92.courses.dao.impl.jdbc.sql.GroupSql;
import com.github.Aleksandra92.courses.exceptions.GroupException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Aleksandra Perova. Created on 03.04.2015.
 */
public class GroupDaoJdbcImpl implements GroupDao {

    @Override
    public void saveOrUpdate(Group group) throws GroupException {
        String updateSql = GroupSql.UPDATE_SQL;
        String insertSql = GroupSql.INSERT_SQL;

        String sql;
        if (group.getId() == null) {
            sql = insertSql;
        } else {
            sql = updateSql;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        } catch (Exception e) {
            throw new GroupException("Unable to save or update", e);
        } finally {
        ConnectionManager.close(con, stmt);
    }

    }

    @Override
    public void delete(Long id) throws GroupException {
        String sql = GroupSql.DELETE_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (Exception e) {
            throw new GroupException("Unable to delete by id", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public void delete(Group group) throws GroupException {
        String sql = GroupSql.DELETE_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, group.getId());
            stmt.execute();
        } catch (Exception e) {
            throw new GroupException("Unable to delete", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public Group get(Long id) throws GroupException {
        String sql = GroupSql.GET_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapGroup(rs);
            }

        } catch (Exception e) {
            throw new GroupException("Unable to get", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
        return null;
    }

    @Override
    public List<Group> getAll() throws GroupException {
        List<Group> groups = new ArrayList<>();

        String sql = GroupSql.GET_ALL_SQL;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                groups.add(mapGroup(rs));
            }
        } catch (Exception e) {
            throw new GroupException("Unable to getAll", e);
        } finally {
            ConnectionManager.close(con, stmt, rs);
        }
        return groups;
    }

    @Override
    public void deleteAll() throws GroupException {
        String sql = GroupSql.DELETE_ALL_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            throw new GroupException("Unable to delete All", e);
        } finally {
            ConnectionManager.close(con, stmt);
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
