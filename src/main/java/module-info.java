module org.testecalculadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens org.testecalculadora to javafx.fxml;
    opens org.testecalculadora.controller to javafx.fxml;
    opens org.testecalculadora.model to javafx.base;

    exports org.testecalculadora;
    exports org.testecalculadora.controller to javafx.fxml;
    exports org.testecalculadora.model to javafx.fxml;
}