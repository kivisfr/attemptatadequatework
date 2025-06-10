package com.attemptatadequatework.attempt_at_adequate_work;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

class TestDataBasePart {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "ratam";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static ObservableList<ObservableList> table;
    public static TableView tableView;


    @BeforeClass
    public static void checkConnectionToDatabase() throws SQLException {

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE test_table (id INT PRIMARY KEY, name VARCHAR(255))");
            statement.executeUpdate("INSERT INTO test_table VALUES (1, 'Test')");
            connection.close();
    }

    @Test
    void testtable_view() throws SQLException {
        DataBasePart.table_view("test_table");
    }

    @AfterClass
    public static void tearDownDatabase() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();

        statement.executeUpdate("DELETE TABLE test_table");
        connection.close();
    }

}