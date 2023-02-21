package ua.edu.znu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement {
    public static void main(String[] args) {
        final String DB_CONNECTION = "jdbc:mysql://localhost:3309jdbcstudy";
        final String DB_USER = "root";
        final String DB_PASSWORD = "God911";
        String insertBusSQL = "INSERT into roles (`type`, `description`) VALUES (?,?, ?)";
        String updateBusRouteSQL = "UPDATE roles SET description = ? WHERE id =?";
        String selectBusSQL = "SELECT * FROM roles WHERE id = ?";
        String deleteBusSQL = "DELETE FROM roles where id=?";
        String selectAllBusesSQL = "SELECT * FROM roles";

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
             PreparedStatement insertBusStmt = connection.prepareStatement(insertBusSQL);
             PreparedStatement updateBusRouteStmt = connection.prepareStatement(updateBusRouteSQL);
             PreparedStatement selectBusStmt = connection.prepareStatement(selectBusSQL);
             PreparedStatement deleteBusStmt = connection.prepareStatement(deleteBusSQL);
             PreparedStatement selectAllBusesStmt = connection.prepareStatement(selectAllBusesSQL)) {
            System.out.println("The " + connection.getCatalog() + " database is connected");
            /*Prepare data to insert*/
            insertBusStmt.setInt(1, 13); insertBusStmt.setString(2, "р546ка"); insertBusStmt.setInt(3, 2);
            /*Insert data*/
            int rows = insertBusStmt.executeUpdate(); System.out.println("\n " + rows + " record(s) inserted");
            readAllFromBusses(selectAllBusesStmt);
            /*Prepare data to update*/
            updateBusRouteStmt.setInt(1, 3); updateBusRouteStmt.setInt(2, 13);
            /*Update data*/
            rows = updateBusRouteStmt.executeUpdate(); System.out.println("\n " + rows + " record(s) updated");
            System.out.println("Data read after insert and update:"); /*Prepare data to read*/
            selectBusStmt.setInt(1, 13);
            /*Read data*/
            ResultSet rs = selectBusStmt.executeQuery();
            rs.next();
        /*You can select data not by name, but by column numbers,
              the first column has number 1*/
            System.out.println("bus_id: " + rs.getInt(1) + ", number: "
                    + rs.getString(2) + ", route_id: " + rs.getInt(3));
            /*Prepare data to delete*/
            deleteBusStmt.setInt(1, 13);
            /*Delete data*/
            rows = deleteBusStmt.executeUpdate();
            System.out.println("\n " + rows + " record(s) deleted");
            System.out.println("Data read after insert and delete:");
            readAllFromBusses(selectAllBusesStmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void readAllFromBusses(final PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int busId = resultSet.getInt("bus_id");
            String busNumber = resultSet.getString("number");
            int routeId = resultSet.getInt("route_id"); System.out.println("bus_id: " + busId + ", number: " + busNumber
                    + ", route_id: " + routeId);
        }
    }
}
