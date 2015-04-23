package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import com.github.Aleksandra92.courses.dao.impl.jdbc.sql.StudentSql;
import com.github.Aleksandra92.courses.exceptions.StudentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 03.04.2015.
 */
public class StudentDaoJdbcImpl implements StudentDao {

    public StudentDaoJdbcImpl(){
    }

    @Override
    public void saveOrUpdate(Student student) throws StudentException {
        String insertSql = StudentSql.INSERT_SQL;
        String updateSql = StudentSql.UPDATE_SQL;

        String sql;
        if (student.getId() == null) {
            sql = insertSql;
        } else {
            sql = updateSql;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getMiddleName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getSex());
            stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
            stmt.setLong(6, student.getGroupId());
            stmt.setInt(7, student.getEducationYear());
            if (student.getId() != null) {
                stmt.setLong(8, student.getId());
            }
            stmt.execute();

            if(student.getId() == null) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (Exception e) {
            throw new StudentException("Unable to save or update", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public void delete(Long id) throws StudentException {
        String sql = StudentSql.DELETE_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (Exception e) {
            throw new StudentException("Unable to delete by id", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public void delete(Student student) throws StudentException {
        String sql = StudentSql.DELETE_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, student.getId());
            stmt.execute();
        } catch (Exception e) {
            throw new StudentException("Unable to delete", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public Student get(Long id) throws StudentException {
        String sql = StudentSql.GET_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapStudent(rs);
            }
        } catch (Exception e) {
            throw new StudentException("Unable to get", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
        return null;
    }

    @Override
    public List<Student> getAll() throws StudentException {
        List<Student> students = new ArrayList<>();
        String sql = StudentSql.GET_ALL_SQL;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (Exception e) {
            throw new StudentException("Unable to getAll", e);
        } finally {
            ConnectionManager.close(con, stmt, rs);
        }
        return students;
    }

    @Override
    public void deleteAll()throws StudentException {
        String sql = StudentSql.DELETE_ALL_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            throw new StudentException("Unable to deleteAll", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public void addAll(List<Student> students) {
        for(Student student: students){
            try {
                saveOrUpdate(student);
            } catch (StudentException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Student> getStudentsFromGroup(Group group, int year) throws StudentException {
        List<Student> students = new ArrayList<>();
        String sql = StudentSql.GET_STUDENTS_FROM_GROUP_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (Exception e) {
            throw new StudentException("Unable to get student from group", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
        return students;
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException {

        String sql = StudentSql.MOVE_STUDENTS_TO_GROUP_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, newGroup.getId());
            stmt.setInt(2, newYear);
            stmt.setLong(3, oldGroup.getId());
            stmt.setInt(4, oldYear);
            stmt.execute();
        } catch (Exception e) {
            throw new StudentException("Unable to move students to group", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) throws StudentException {
        String sql = StudentSql.REMOVE_STUDENTS_FROM_GROUP_SQL;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionManager.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            stmt.execute();
        } catch (Exception e) {
            throw new StudentException("Unable to remove students from group", e);
        } finally {
            ConnectionManager.close(con, stmt);
        }
    }

    private Student mapStudent(ResultSet rs) throws SQLException {
        Student st = new Student();
        st.setId(rs.getLong(1));
        st.setLastName(rs.getString(3));
        st.setFirstName(rs.getString(2));
        st.setMiddleName(rs.getString(4));
        st.setSex(rs.getString(5));
        st.setDateOfBirth(rs.getDate(6));
        st.setGroupId(rs.getLong(7));
        st.setEducationYear(rs.getInt(8));

        return st;
    }
}
