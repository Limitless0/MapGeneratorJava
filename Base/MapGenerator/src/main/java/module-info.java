module MapGenerator {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens dev.iskander.mgj to javafx.fxml;
    exports dev.iskander.mgj;
}