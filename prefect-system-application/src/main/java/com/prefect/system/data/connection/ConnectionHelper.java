package com.prefect.system.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:rogate";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Changeme0";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void rollbackTransaction(SQLException e) {
    }
}
