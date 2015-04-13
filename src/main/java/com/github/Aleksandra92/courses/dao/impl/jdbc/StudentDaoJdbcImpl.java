package com.github.Aleksandra92.courses.dao.impl.jdbc;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.dao.StudentDao;
import com.github.Aleksandra92.courses.exceptions.StudentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 03.04.2015.
 */
public class StudentDaoJdbcImpl implements StudentDao {

    private static Connection con;

    public StudentDaoJdbcImpl() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/course";
            con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void saveOrUpdate(Student student) throws StudentException {
        String insertSql = "INSERT INTO students " +
                "(first_name, middle_name, last_name, sex, date_of_birth, group_id, education_year)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE students SET " +
                "first_name=?, middle_name=?, last_name=?, " +
                "sex=?, date_of_birth=?, group_id=?, education_year=? "+
                "WHERE student_id=?";

        try {
            PreparedStatement stmt;
            if (student.getId() == null) {
                stmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            } else {
                stmt = con.prepareStatement(updateSql);
            }
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
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Long id) throws StudentException {
        String sql = "DELETE FROM students WHERE student_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to delete by id", e);
        }
    }

    @Override
    public void delete(Student student) throws StudentException {
        String sql = "DELETE FROM students WHERE student_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, student.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to delete", e);
        }
    }

    @Override
    public Student get(Long id) throws StudentException {
        String sql = "SELECT student_id, first_name, middle_name, last_name, " +
                "sex, date_of_birth, group_id, education_year FROM students " +
                "WHERE student_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapStudent(rs);
            }
        } catch (SQLException e) {
            throw new StudentException("Unable to get", e);
        }
        return null;
    }

    @Override
    public List<Student> getAll() throws StudentException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, first_name, middle_name, last_name, " +
                "sex, date_of_birth, group_id, education_year FROM students " +
                "ORDER BY last_name, first_name, middle_name";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new StudentException("Unable to getAll", e);
        }
        return students;
    }

    @Override
    public void deleteAll()throws StudentException {
        String sql = "TRUNCATE TABLE students";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to deleteAll", e);
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
        String sql = "SELECT student_id, first_name, middle_name, last_name, " +
                "sex, date_of_birth, group_id, education_year FROM students " +
                "WHERE group_id=? AND education_year=? " +
                "ORDER BY last_name, first_name, middle_name";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new StudentException("Unable to get student from group", e);
        }
        return students;
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException {

        String sql = "UPDATE students SET group_id=?, education_year=? " +
                "WHERE group_id=? AND education_year=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, newGroup.getId());
            stmt.setInt(2, newYear);
            stmt.setLong(3, oldGroup.getId());
            stmt.setInt(4, oldYear);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to move students to group", e);
        }
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) throws StudentException {
        String sql = "DELETE FROM students WHERE group_id=? AND education_year=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to remove students from group", e);
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
