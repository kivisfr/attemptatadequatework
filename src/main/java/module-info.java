module com.attemptatadequatework.attempt_at_adequate_work {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.attemptatadequatework.attempt_at_adequate_work to javafx.fxml;
    exports com.attemptatadequatework.attempt_at_adequate_work;
}