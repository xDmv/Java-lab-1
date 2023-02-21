package ua.edu.znu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ResultSetCursor {
    public static void main(String[] args) {
        final String DB_CONNECTION = "jdbc:mysql://localhost:3309/jdbcstudy";
        final String DB_USER = "root";
        final String DB_PASSWORD = "God911";

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                /*The java.sql.Statement object is used scrollable ResultSet
                  and the ability to change data through the ResultSet*/
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("The " + connection.getCatalog() + " database is connected");
            System.out.println("\nRead data:");
            String strSQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(strSQL);
            System.out.println("Move cursor at last row..."); resultSet.last();
            printRow(resultSet);
            System.out.println("Move cursor at first row..."); resultSet.first();
                printRow(resultSet);
            System.out.println("Update role_id value in the first row...");
            /*Update data through ResultSet - they updated in database also*/
            resultSet.updateInt("role_id", 7);
                    resultSet.updateRow();
                printRow(resultSet);
            System.out.println("Move cursor at the next row..."); resultSet.next();
                printRow(resultSet);
            System.out.println("Move cursor at the previous row..."); resultSet.previous();
                printRow(resultSet);
            System.out.println("Move cursor at the 4-th row..."); resultSet.absolute(4);
                printRow(resultSet);
            System.out.println("Move the cursor to the beginning " + "of the ResultSet before the first row...");
               resultSet.beforeFirst();
            System.out.println("Move cursor at first row..."); resultSet.next();
                printRow(resultSet);
            System.out.println("Move the cursor to the end of the ResultSet "
                    + "field of the last row..."); resultSet.afterLast();
            System.out.println("ResultSet is automatically closed "
                    + "when its parent Statement is closed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    private static void printRow(ResultSet resultSet) throws SQLException { int userId = resultSet.getInt("id");
        String first_name = resultSet.getString("first_name");
        int roleId = resultSet.getInt("role_id");
        System.out.println("user_id: " + userId + ", first_name: " + first_name
                + ", role_id: " + roleId);
    }
}
