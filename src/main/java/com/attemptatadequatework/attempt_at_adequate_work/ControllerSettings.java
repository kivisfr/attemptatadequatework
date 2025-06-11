package com.attemptatadequatework.attempt_at_adequate_work;


    /*
        Класс, предназначенный для настройки элементов пользовательского интерфейса.
     */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class ControllerSettings {

    private static String tableName;

    @FXML
    public static ComboBox<String> comboBoxColumns;

    @FXML
    public TextArea textArea;

    @FXML
    public Pane mainPane;

    @FXML
    private HBox StandardHBox;

    @FXML
    private TableView StandardTable;


    @FXML
    protected void onButtonClick(ActionEvent event) {
         try {
             /*
                При помощи отображаемого текста на кнопке определяется, какая именно была нажата.
              */
            Button clickedButton = (Button) event.getSource();
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
                case "add":
                    try{

                    } catch (Exception e){

                    }
                    break;
                default:
                   break;
            }

            textArea.setText("Selected table is " + tableName + ".");
            DataBasePart.table_view(tableName);

            TableView tableView = DataBasePart.tableView;

            int tableIndex = StandardHBox.getChildren().indexOf(StandardTable);
            StandardHBox.getChildren().set(tableIndex, tableView);

             int comboBoxIndex = mainPane.getChildren().indexOf(comboBoxColumns);
             mainPane.getChildren().set(comboBoxIndex, comboBoxColumns);

        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    protected static void onAddButtonClicked(){

    }
}