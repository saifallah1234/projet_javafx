module FSB.pro {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens FSB.pro.controllers to javafx.fxml;
    exports FSB.pro.Main;
    exports FSB.pro.controllers;
}