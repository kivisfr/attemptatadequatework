package com.attemptatadequatework.attempt_at_adequate_work;


    /*
        Класс, предназначенный для настройки элементов пользовательского интерфейса.
     */

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class ControllerSettings {

    @FXML
    private Button  activatedTableButton, activatedButtonTwo;

    @FXML
    public TextArea textArea;

    @FXML
    private HBox StandardHBox;

    @FXML
    private TableView StandardTable;


    @FXML
    protected void onButtonClick(String tableName) {
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


}