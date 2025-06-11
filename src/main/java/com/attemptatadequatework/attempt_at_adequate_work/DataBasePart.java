package com.attemptatadequatework.attempt_at_adequate_work;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

/*
    Класс, предназначенный для работы с базой данной (MySQL).

    Методы:
            table_view(tableName) — создаёт таблицу TableView через javafx.
                        принимает String tableName — название таблицы для отображения.
 */

public class DataBasePart {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "ratam";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static ObservableList<ObservableList> table;
    public static TableView tableView;
    public static ObservableList<String> tableColumnsNames =
            FXCollections.observableArrayList();
    /*
        Динамическое создание таблицы с базы данных в отображаемую через javafx таблицу.
     */
    public static void table_view(String tableName) throws SQLException {

        String sqlCommand = "SELECT * FROM trying." + tableName;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            tableView = new TableView();
            table = FXCollections.observableArrayList();

            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        Object value = param.getValue().get(j);
                        return new SimpleStringProperty(value == null ? "" : value.toString());
                    }
                });

                tableView.getColumns().addAll(col);
            }

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
                if (tableColumnsNames.size() <= resultSet.getMetaData().getColumnCount()){
                    tableColumnsNames.add(resultSet.getMetaData().getColumnName(i));
                }
            }

             while (resultSet.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(resultSet.getString(i));
                }
                table.add(row);
            }




            tableView.setItems(table);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on building table.");
            Throwable cause = e.getCause();
            System.err.println(cause.getMessage());
        }
    }
}
