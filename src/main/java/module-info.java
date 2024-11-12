module klemmr02.klemmr02 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens klemmr02.klemmr02 to javafx.fxml;
    exports klemmr02.klemmr02;
}
