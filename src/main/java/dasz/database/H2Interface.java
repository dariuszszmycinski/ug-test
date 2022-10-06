package dasz.database;

import dasz.model.Computer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Interface {

    public void createTable() {
        String createTableSQL =
                //"DROP TABLE IF EXISTS computers;"+
                "CREATE TABLE IF NOT EXISTS computers (\n" +
                        "  nazwa VARCHAR(250) NOT NULL,\n" +
                        "  data_ksiegowania VARCHAR(10) NOT NULL,\n" +
                        "  koszt_USD DOUBLE NOT NULL,\n" +
                        "  koszt_PLN DOUBLE NOT NULL\n" +
                        "   \n" +
                        ");";
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void addComputer(Computer computer) {
        String addComputer = "INSERT INTO computers  VALUES\n" +
                "  ('" + computer.getName() + "', '" + computer.getDate() + "', '" + computer.getCostUSD() + "', '" + computer.getCostPLN() + "')";
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(addComputer);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public List<Computer> showComputersByName(String name) {
        String showComputers = "SELECT * FROM computers WHERE nazwa LIKE '%" + name + "%'";
        List<Computer> computers = new ArrayList<>();
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(showComputers);
            while (resultSet.next()) {
                computers.add(new Computer(resultSet.getString(1), resultSet.getString(2),  Double.parseDouble(resultSet.getString(3)), Double.parseDouble(resultSet.getString(4))));
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        return computers;
    }

    public List<Computer> showComputersByDate(String date) {
        String showComputers = "SELECT * FROM computers WHERE data_ksiegowania LIKE '%" + date + "%'";
        List<Computer> computers = new ArrayList<>();
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(showComputers);
            while (resultSet.next()) {
                computers.add(new Computer(resultSet.getString(1), resultSet.getString(2),  Double.parseDouble(resultSet.getString(3)), Double.parseDouble(resultSet.getString(4))));
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        return computers;
    }

}
