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

    private StudentDaoJdbcImpl() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            Connection con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<Student> getStudentsFromGroup(Group group, int year) throws StudentException {
        List<Student> students = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "SELECT student_id, firstName, middleName, lastName, " +
                            "sex, dateOfBirth, group_id, educationYear FROM students " +
                            "WHERE group_id=? AND educationYear=? " +
                            "ORDER BY lastName, firstName, middleName");
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
        return students;
    }

    @Override
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws StudentException {

        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE students SET group_id=?, educationYear=? " +
                            "WHERE group_id=? AND educationYear=?");
            stmt.setLong(1, newGroup.getId());
            stmt.setInt(2, newYear);
            stmt.setLong(3, oldGroup.getId());
            stmt.setInt(4, oldYear);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public void removeStudentsFromGroup(Group group, int year) throws StudentException {

        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM students WHERE group_id=? AND educationYear=?");
            stmt.setLong(1, group.getId());
            stmt.setInt(2, year);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public void saveOrUpdate(Student student) throws StudentException {

        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE students SET " +
                            "firstName=?, middleName=?, lastName=?, " +
                            "sex=?, dateOfBirth=?, group_id=?, educationYear=?" +
                            "WHERE student_id=?");
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getMiddleName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, new String(new char[]{student.getSex()}));
            stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
            stmt.setLong(6, student.getGroupId());
            stmt.setInt(7, student.getEducationYear());
            stmt.setLong(8, student.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Long id) throws StudentException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM students WHERE student_id=?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public void delete(Student student) throws StudentException {

    }

    @Override
    public Student get(Long id) throws StudentException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT get_id, firstName, middleName, lastName, " +
                            "sex, dateOfBirth, group_id, educationYear FROM students " +
                            "ORDER BY lastName, firstName, middleName ");
            return mapStudent(rs);
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
    }

    @Override
    public List<Student> getAll() throws StudentException {
        List<Student> students = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT get_id, firstName, middleName, lastName, " +
                            "sex, dateOfBirth, group_id, educationYear FROM students " +
                            "ORDER BY lastName, firstName, middleName");
            while (rs.next()) {
                students.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new StudentException("Unable to save or update", e);
        }
        return students;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void addAll(List<Student> entity) {

    }

    private Student mapStudent(ResultSet rs) throws SQLException {
        Student st = new Student();
        st.setLastName(rs.getString(4));
        return st;
    }
}
