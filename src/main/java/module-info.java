module com.mycompany.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.project2 to javafx.fxml;
    exports com.mycompany.project2;
}
