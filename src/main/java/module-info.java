module com.example.photoviewer_viewcontroller {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.photoviewer_viewcontroller to javafx.fxml;
    exports com.example.photoviewer_viewcontroller;
}