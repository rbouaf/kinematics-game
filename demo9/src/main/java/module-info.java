module com.example.demo9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalProject to javafx.fxml;
    exports com.example.finalProject;
}