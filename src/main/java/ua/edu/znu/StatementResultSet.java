package ua.edu.znu;

import java.sql.*;

public class StatementResultSet {
    public static void main(String[] args) {
        final String DB_CONNECTION = "jdbc:mysql://localhost:3309/jdbcstudy";
        final String DB_USER = "root";
        final String DB_PASSWORD = "God911";

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) { System.out.println("The " + connection.getCatalog()
                + " database is connected");
            /*Add data*/
            String strSQL = "INSERT INTO roles (`type`, `description`) VALUES ('admins', 'administration')";
            statement.executeUpdate(strSQL);

            strSQL = "INSERT INTO roles (`type`, `description`) VALUES ('clients', 'all clients')";
            boolean res = statement.execute(strSQL);
            /*Read data*/

            System.out.println("Data read after insert, is ResultSet returned: "
                    + res); readAllFromBusses(statement);
            /*Update data*/
            strSQL = "UPDATE roles SET `type` = 'admin', `description` = 'delete duplicate' WHERE id = 5";
            statement.executeUpdate(strSQL);
           // System.out.println("\n " + rows + " record(s) updated");
            System.out.println("Data read after update:");
            readAllFromBusses(statement);
            /*Delete data*/
            strSQL = "DELETE FROM roles where `id`= 8";
            statement.executeUpdate(strSQL);
            System.out.println("Data read after delete:");
            readAllFromBusses(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void readAllFromBusses(final Statement statement)
            throws SQLException {
        String strSQL = "SELECT * FROM roles";
        /*ResultSet auto closed after it Statement close*/
        ResultSet resultSet = statement.executeQuery(strSQL); while (resultSet.next()) {
            int Id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String description = resultSet.getString("description");
            System.out.println("bus_id: " + Id + ", type: " + type +
                    ", description: " + description);
        }
    }
}
