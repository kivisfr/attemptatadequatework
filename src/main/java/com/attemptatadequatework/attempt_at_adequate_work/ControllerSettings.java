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
    public ComboBox<String> comboBoxColumns;

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
             //     Очищение всех элементов ComboBox & StandardTable перед последующим заполнением.
             comboBoxColumns.getItems().clear();
             StandardTable.getItems().clear();

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

            comboBoxColumns.setItems(DataBasePart.tableColumnsNames);

             // StandardTable.setItems(DataBasePart.tableView.getItems());

             // StandardTable.itemsProperty().bind(DataBasePart.tableView.itemsProperty());

             StandardTable.setItems(DataBasePart.table);

             TableView tableView = DataBasePart.tableView;
             //StandardTable = tableView;

            int tableIndex = StandardHBox.getChildren().indexOf(StandardTable);
            StandardHBox.getChildren().set(tableIndex, tableView);

            int comboBoxIndex = mainPane.getChildrenUnmodifiable().indexOf(comboBoxColumns);
            mainPane.getChildren().set(comboBoxIndex, comboBoxColumns);

        } catch (SQLException e) {
             System.out.println("Error on Controller code part.");
                throw new RuntimeException(e);
        }
    }

    protected static void onAddButtonClicked(){

    }
}