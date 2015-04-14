package com.github.Aleksandra92.courses.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Aleksandra Perova. Created on 13.04.2015.
 */
public class ConnectionManager {

    public static final String URL = "jdbc:mysql://localhost:3306/course";

    public ConnectionManager() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "root", "root");
    }
}
