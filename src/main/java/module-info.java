module com.example.restarauntsys {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.restarauntsys to javafx.fxml;
    opens com.example.restarauntsys.tables to javafx.fxml;

    exports com.example.restarauntsys;
    exports com.example.restarauntsys.tables;

}