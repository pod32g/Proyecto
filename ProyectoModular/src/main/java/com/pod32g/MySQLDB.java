package com.pod32g;

import java.sql.*;

public class MySQLDB implements Database {

    private final String DB_URL = "jdbc:mysql://localhost/feedback?user=root&password=";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public void runInsertQuery(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection = null;
            }
            if (preparedStatement != null) {
                preparedStatement = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
