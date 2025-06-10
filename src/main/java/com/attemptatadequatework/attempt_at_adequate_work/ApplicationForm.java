package com.attemptatadequatework.attempt_at_adequate_work;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    /*
        Класс, предназначенный для работы с пользователским интерфейсов,
        доступный через использование javafx.

        Включает методы:
            start(Stage stage) throws IOException — создание интерфейса.
            main(String[] args) — иницилизация, начало работы программы.
     */

public class ApplicationForm extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(ApplicationForm.class.getResource("formSettings.fxml"));
        Scene scene = new
                Scene(fxmlLoader.load(), 320, 240);


        stage.setTitle("Information archive.");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}