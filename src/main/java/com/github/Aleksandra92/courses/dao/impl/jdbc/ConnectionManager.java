package com.github.Aleksandra92.courses.dao.impl.jdbc;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Author: Aleksandra Perova. Created on 13.04.2015.
 */
public class ConnectionManager {

    private static DataSource ds;

    private ConnectionManager() throws Exception {
        InitialContext initContext = new InitialContext();
        ds = (DataSource) initContext.lookup("java:comp/env/jdbc/course");
    }

    public static ConnectionManager getInstance() {
        return Handler.instance;
    }

    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    public static void close(Connection con, Statement stmt) {
        close(con, stmt, null);
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ignored) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignored) {
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignored) {
            }
        }
    }

    private static class Handler {
        private static ConnectionManager instance;

        static {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                System.err.println("Error: Exception in ConnectionManager constructor.");
            }
        }
    }
}
