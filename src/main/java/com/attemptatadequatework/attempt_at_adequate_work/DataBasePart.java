package com.attemptatadequatework.attempt_at_adequate_work;


    /*
        Класс, предназначенный для работы с базой данной (MySQL).

     */

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBasePart {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "ratam";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static ObservableList<ObservableList> table;
    public static TableView tableView;

    /*

     */
    public static void connect(){
        try {

        } catch (){

        }
    }
}
