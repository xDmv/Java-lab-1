package ua.edu.znu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("test message!");
        System.out.println("The " + getConnection() + " database is connected");

    }

    public static Connection getConnection() throws SQLException {
        final String DB_CONNECTION = "jdbc:mysql://localhost:3309/jdbcstudy";
        final String DB_USER = "root";
        final String DB_PASSWORD = "God911";

        Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        return connection;
    }
}