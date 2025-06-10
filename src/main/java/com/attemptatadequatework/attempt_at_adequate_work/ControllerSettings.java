package com.attemptatadequatework.attempt_at_adequate_work;


    /*
        Класс, предназначенный для настройки элементов пользовательского интерфейса.
     */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class ControllerSettings {

    private static String tableName;

    @FXML
    private Button  activatedTableButton, activatedButtonTwo;

    @FXML
    public TextArea textArea;

    @FXML
    private HBox StandardHBox;

    @FXML
    private TableView StandardTable;


    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        try {
            switch (clickedButton.getText()) {
                case "users":
                    tableName = "users";
                    break;
                case "transactions":
                    tableName = "transactions";
                    break;
                case "books":
                    tableName = "books";
                    break;
                case "book_information":
                    tableName = "book_information";
                    break;
                default:
                   break;
            }

            textArea.setText("Selected table " + tableName + " .");
            DataBasePart.table_view(tableName);

            TableView tableView = DataBasePart.tableView;

            int tableIndex = StandardHBox.getChildren().indexOf(StandardTable);
            StandardHBox.getChildren().set(tableIndex, tableView);

        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }


}