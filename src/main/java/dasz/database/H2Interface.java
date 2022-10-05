package dasz.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Interface {

    public static void main(String[] argv) throws SQLException {
        H2Interface h2Interface = new H2Interface();
        h2Interface.createTable();
        h2Interface.addComputer();
        h2Interface.showComputers();
    }

    public void createTable() throws SQLException {
        String createTableSQL =
                //   "DROP TABLE IF EXISTS computers;"+
                "CREATE TABLE IF NOT EXISTS computers (\n" +
                        "  nazwa VARCHAR(250) NOT NULL,\n" +
                        "  data_ksiegowania VARCHAR(250) NOT NULL,\n" +
                        "  koszt_USD VARCHAR(250) NOT NULL,\n" +
                        "  koszt_PLN VARCHAR(250) NOT NULL\n" +
                        "   \n" +
                        ");";
        System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {
            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void addComputer() throws SQLException {
        String addComputer = "INSERT INTO computers  VALUES\n" +
                "  ('computer3', '2022-01-01', '4.444', '5.555')";
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(addComputer);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void showComputers() throws SQLException {
        String showComputers = "SELECT * FROM computers";
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(showComputers);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4));
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

}
