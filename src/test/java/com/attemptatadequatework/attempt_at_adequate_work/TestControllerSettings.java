package com.attemptatadequatework.attempt_at_adequate_work;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class TestControllerSettings {

    @FXML
    private static TextArea textArea;

    @FXML
    private HBox StandardHBox;

    @FXML
    private TableView StandardTable;


    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "ratam";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static ObservableList<ObservableList> table;
    public static TableView tableView;
    public static Stage stage;


    @BeforeClass
    public static void checkConnectionToDatabase() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE test_table (id INT PRIMARY KEY, name VARCHAR(255))");
        statement.executeUpdate("INSERT INTO test_table VALUES (1, 'Test')");
        connection.close();
    }

    @BeforeClass
    public static void teststart() throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(ApplicationForm.class.getResource("formSettings.fxml"));
        Scene scene = new
                Scene(fxmlLoader.load(), 320, 240);


        stage.setTitle("Information archive.");
        stage.setScene(scene);
        stage.show();

        textArea = fxmlLoader.getController();
    }

    @Test
    void testonButtonClick() {
        textArea.setText("Selected table " + "test_table" + " .");

        try {
            DataBasePart.table_view("test_table");

            TableView tableView = DataBasePart.tableView;

            int tableIndex = StandardHBox.getChildren().indexOf(StandardTable);
            StandardHBox.getChildren().set(tableIndex, tableView);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void tearDownDatabase() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();

        statement.executeUpdate("DELETE TABLE test_table");
        connection.close();
    }
}