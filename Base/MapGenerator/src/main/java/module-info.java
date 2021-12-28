module MapGenerator {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires CanvasDrawifier;
    uses dev.iskander.canvasDrawifier.CanvasDrawifier;

    opens dev.iskander.mgj to javafx.fxml;
    exports dev.iskander.mgj;
}