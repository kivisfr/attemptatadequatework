package com.attemptatadequatework.attempt_at_adequate_work;


    /*
        Класс, предназначенный для настройки элементов пользовательского интерфейса.
     */

import com.sun.source.tree.IfTree;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


public class ControllerSettings {

    private String tableName;
    public ArrayList<String> tableColumnsName;
    public Integer columnsCount;
    protected TableView tableView;

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
    public TextField textField;

    @FXML
    public Button addButton;

    @FXML
    protected void onButtonClick(ActionEvent event) {
         try {
             /*
                При помощи отображаемого текста на кнопке определяется, какая именно была нажата.
              */
            Button clickedButton = (Button) event.getSource();
             //     Очищение всех элементов ComboBox & TableView перед последующим заполнением.
             comboBoxColumns.getItems().clear();
             StandardTable.getItems().clear();
             if (tableColumnsName != null && !tableColumnsName.isEmpty()) {
                  tableColumnsName.clear();
             }
             columnsCount = 0;

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

            textArea.setText("Selected table is " + tableName + ".");
            DataBasePart.table_view(tableName);


            comboBoxColumns.setItems(DataBasePart.tableColumnsNames);

             // StandardTable.setItems(DataBasePart.tableView.getItems());

             // StandardTable.itemsProperty().bind(DataBasePart.tableView.itemsProperty());

             StandardTable.setItems(DataBasePart.table);

             tableView = DataBasePart.tableView;
             //StandardTable = tableView;
             columnsCount = tableView.getColumns().size();
             //  tableColumnsName = new ArrayList<String>(tableView.getColumns());

             ObservableList<TableColumn<?, ?>> columns = tableView.getColumns();

             tableColumnsName = new ArrayList<String>();
             for (TableColumn<?, ?> column : columns) {
                 tableColumnsName.add(column.getText());
             }

            int tableIndex = StandardHBox.getChildren().indexOf(StandardTable);
            StandardHBox.getChildren().set(tableIndex, tableView);

            int comboBoxIndex = mainPane.getChildrenUnmodifiable().indexOf(comboBoxColumns);
            mainPane.getChildren().set(comboBoxIndex, comboBoxColumns);

        } catch (SQLException e) {
             System.out.println("Error on Controller code part.");
                throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onAddButtonClicked(ActionEvent event) throws SQLException {
    String columnName = comboBoxColumns.getValue();
    String addInformation = textField.getText();
        Alert alert;
    if (columnName == null || columnName.isEmpty()){
        alert = new Alert(Alert.AlertType.INFORMATION, "Please, choose the column name.");
        alert.showAndWait();
    } else if(addInformation.isEmpty()){
        alert = new Alert(Alert.AlertType.INFORMATION, "Please, write an information to add.");
        alert.showAndWait();
    } else if (tableColumnsName.isEmpty() || columnsCount == 0){
        alert = new Alert(Alert.AlertType.INFORMATION, "Please, choose the table.");
        alert.showAndWait();
    } else {
        DataBasePart.setConnection(tableName);

        StringBuilder columnNamesForCommand = new StringBuilder();
        StringBuilder addInformationForCommand = new StringBuilder();
        for (int i = 0; i < columnsCount; i++){
            if (columnName.equals(tableColumnsName.get(i))){
                columnNamesForCommand.append(tableColumnsName.get(i));
                addInformationForCommand.append("'" + addInformation + "'");
            } else {
                columnNamesForCommand.append(tableColumnsName.get(i));
                addInformationForCommand.append("'—'");
               }
            if (i + 1 != columnsCount){
                columnNamesForCommand.append(", ");
                addInformationForCommand.append(", ");
            }
        }
        String sqlCommandInsert = "INSERT INTO trying." + tableName + " " + "(" + columnNamesForCommand.toString() + ")"
                + " " + "VALUES" + " " + "(" + addInformationForCommand.toString() + ")";
        System.out.println(sqlCommandInsert);
        DataBasePart.workWithTable(sqlCommandInsert, tableName);

    }



    }
}